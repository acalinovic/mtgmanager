package be.bbs.bbsfx.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.hibernate.type.BigIntegerType;

import be.bbs.bbsfx.repository.GenericRepositoryInterface;
import be.bbs.mtgmanager.model.entity.*;
import be.bbs.mtgmanager.repositoy.GenericRepositoryImpl;

public class JsonLoader {

	private static BigInteger multiverseIdTemp;

	private static GenericRepositoryImpl<Artist> artistRepo;
	private static GenericRepositoryImpl<BorderColor> borderColorRepo;;
	private static GenericRepositoryImpl<Color> colorRepo;
	private static GenericRepositoryImpl<Card> cardRepo;
	private static GenericRepositoryImpl<Expansion> expansionRepo;
	private static GenericRepositoryImpl<ExpansionType> expansionTypeRepo;
	private static GenericRepositoryImpl<ForeignData> foreignDataRepo;
	private static GenericRepositoryImpl<FrameVersion> frameVersionRepo;
	private static GenericRepositoryImpl<Language> languageRepo;
	private static GenericRepositoryImpl<Layout> layoutRepo;
	private static GenericRepositoryImpl<Legality> legalityRepo;
	private static GenericRepositoryImpl<Rarity> rarityRepo;
	private static GenericRepositoryImpl<Ruling> rulinRepo;
	private static GenericRepositoryImpl<Token> tokenRepo;
	private static GenericRepositoryImpl<Type> typeRepo;
	private static JsonNode expansions;

	private static File jsonFile;
	private static byte[] jsonData;
	private static ObjectMapper mapper;

	public static JsonNode init() throws IOException {

		return expansions;
	}

	public static void load() throws IOException {

		multiverseIdTemp = new BigInteger("90000000");

		artistRepo = new GenericRepositoryImpl<Artist>();
		borderColorRepo = new GenericRepositoryImpl<BorderColor>();
		colorRepo = new GenericRepositoryImpl<Color>();
		cardRepo = new GenericRepositoryImpl<Card>();
		expansionRepo = new GenericRepositoryImpl<Expansion>();
		expansionTypeRepo = new GenericRepositoryImpl<ExpansionType>();
		foreignDataRepo = new GenericRepositoryImpl<ForeignData>();
		frameVersionRepo = new GenericRepositoryImpl<FrameVersion>();
		languageRepo = new GenericRepositoryImpl<Language>();
		layoutRepo = new GenericRepositoryImpl<Layout>();
		legalityRepo = new GenericRepositoryImpl<Legality>();
		rarityRepo = new GenericRepositoryImpl<Rarity>();
		rulinRepo = new GenericRepositoryImpl<Ruling>();
		tokenRepo = new GenericRepositoryImpl<Token>();
		typeRepo = new GenericRepositoryImpl<Type>();

		jsonFile = new File("/home/boris/Bureau/AllSets.json");
		jsonData = Files.readAllBytes(jsonFile.toPath());

		mapper = new ObjectMapper();
		expansions = mapper.readTree(jsonData);

		loadExpansions(expansions);

	}

	private static void loadExpansions(JsonNode expansions) {

		Iterator<JsonNode> expansionIterator = expansions.elements();

		while (expansionIterator.hasNext()) {
			JsonNode expansion = expansionIterator.next();
			Expansion expansionEntity = new Expansion();

			ExpansionType existingExpansionTypeEntity = expansionTypeRepo.findOneBy(ExpansionType.class,
					"expansionType_name", expansion.get("type").asText());
			if (existingExpansionTypeEntity == null) {
				ExpansionType expansionTypeEntity = new ExpansionType();
				expansionTypeEntity.setExpansiontypeName(expansion.get("type").asText());
				expansionTypeEntity.setExpansiontypeLabel(expansion.get("type").asText().substring(0, 1).toUpperCase()
						+ expansion.get("type").asText().substring(1).replaceAll("_", " "));
				expansionTypeRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, expansionTypeEntity);
				expansionEntity.setExpansionType(expansionTypeEntity);
			} else {
				expansionEntity.setExpansionType(existingExpansionTypeEntity);
			}
			try {
				expansionEntity.setExpansionBasesetsize(expansion.get("baseSetSize").bigIntegerValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				expansionEntity.setExpansionBlock(expansion.get("block").asText());
			} catch (Exception e) {
				expansionEntity.setExpansionBlock("");
			}
			try {
				JsonNode occurences = expansion.get("boosterV3");
				ArrayList<String> rarityOccurences = new ArrayList<>();
				if (!expansion.get("boosterV3").isMissingNode()) {
					for (JsonNode occurence : occurences) {
						if (occurence.isArray()) {
							ArrayList<String> subRarityOccurences = new ArrayList<String>();
							for (JsonNode choice : occurence) {
								subRarityOccurences.add(choice.asText());
							}
							rarityOccurences.addAll(subRarityOccurences);
						} else {
							rarityOccurences.add(occurence.asText());
						}
					}
					expansionEntity.setExpansionBoosterv3(rarityOccurences.toString());
				}
			} catch (Exception e) {
				expansionEntity.setExpansionBoosterv3("");
			}
			try {
				expansionEntity.setExpansionCode(expansion.get("code").asText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (expansion.has("isOnlineOnly") && expansion.get("isOnlineOnly").asBoolean() == true) {
					expansionEntity.setExpansionIsonlineonly(true);
				} else {
					expansionEntity.setExpansionIsonlineonly(false);
				}
			} catch (Exception e) {
			}
			try {
				expansionEntity.setExpansionMeta(expansion.get("mtgoCode").asText());
			} catch (Exception e) {
				expansionEntity.setExpansionMeta("");
			}
			try {
				expansionEntity.setExpansionName(expansion.get("name").asText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				expansionEntity.setExpansionReleasedate(expansion.get("releaseDate").asText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				expansionEntity.setExpansionTotalsetsize(expansion.get("totalSetSize").bigIntegerValue());
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (expansionRepo.findOneBy(Expansion.class, "expansion_code",
					expansionEntity.getExpansionCode()) == null) {
				expansionRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, expansionEntity);
			} else {
				System.out.println("Expansion" + expansionEntity.getExpansionName() + "already exists");
			}
			
			JsonNode cards = expansion.get("cards");
			loadCards(cards);
		}
	}

	private static void loadCards(JsonNode cards) {


		ArrayList<Card> cardCollection = new ArrayList<Card>();

		Iterator<JsonNode> cardIterator = cards.elements();

		while (cardIterator.hasNext()) {
			JsonNode card = cardIterator.next();
			Card cardEntity = new Card();

			Artist existingArtist = artistRepo.findOneBy(Artist.class, "artist_name", card.get("artist").asText());
			if (existingArtist == null) {
				Artist artistEntity = new Artist();
				artistEntity.setArtistName(card.get("artist").asText());
				artistRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, artistEntity);
			} else {
				cardEntity.setArtist(existingArtist);
			}

			BorderColor existingBorderColor = borderColorRepo.findOneBy(BorderColor.class, "bordercolor_name",
					card.get("borderColor").asText()); //
			System.out.println(existingBorderColor);
			if (existingBorderColor == null) {
				BorderColor borderColorEntity = new BorderColor();
				borderColorEntity.setbordercolorName(card.get("borderColor").asText());
				borderColorRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, borderColorEntity);
			} else {
				cardEntity.setBorderColor(existingBorderColor);
			}

			JsonNode colors = card.get("colors");
			ArrayList<Color> colorCollection = new ArrayList<Color>();

			Iterator<JsonNode> colorIterator = colors.elements();

			while (colorIterator.hasNext()) {
				JsonNode color = colorIterator.next();
				Color existingColorEntity = colorRepo.findOneBy(Color.class, "color_code", color.asText());
				if (existingColorEntity == null) {
					Color colorEntity = new Color();
					colorEntity.setColorCode(color.asText().charAt(0));
					switch (color.asText().charAt(0)) {
					case 'R':
						colorEntity.setColorLabel("Red");
						break;
					case 'G':
						colorEntity.setColorLabel("Green");
						break;
					case 'B':
						colorEntity.setColorLabel("Black");
						break;
					case 'W':
						colorEntity.setColorLabel("White");
						break;
					case 'U':
						colorEntity.setColorLabel("Blue");
						break;
					default:
						break;
					}
					colorRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, colorEntity);
					colorCollection.add(colorEntity);
				} else {
					colorCollection.add(existingColorEntity);
				}
			}
			cardEntity.setColors(colorCollection);

			cardEntity.setCardConvertedmanacost(card.get("convertedManaCost").asDouble());

			if (card.has("faceConvertedManaCost")) {
				cardEntity.setCardFaceconvertedmanacost(card.get("faceConvertedManaCost").asDouble());
			} else {
				cardEntity.setCardFaceconvertedmanacost(card.get("convertedManaCost").asDouble());
			}
			if (card.has("flavorText")) {
				cardEntity.setCardFlavortext(card.get("flavorText").asText());
			}
			JsonNode foreignDatas = card.get("foreignData");

			Iterator<JsonNode> foreignDataIterator = foreignDatas.elements();
			ArrayList<ForeignData> foreignDataCollection = new ArrayList<ForeignData>();
			while (foreignDataIterator.hasNext()) {
				JsonNode foreignData = foreignDataIterator.next();

				if (card.has("foreignData") || card.get("foreignData") != null) { //
					System.out.println(new BigInteger(foreignData.get("multiverseId").asText()));
					ForeignData existingForeignData = null;
					if (foreignData.has("multiverseId")) {
						existingForeignData = foreignDataRepo.findOneBy(ForeignData.class, "foreigndata_multiverseid",
								new BigInteger(foreignData.get("multiverseId").asText()));
					} else {
						multiverseIdTemp = multiverseIdTemp.add(new BigInteger("1"));
						// System.out.println(multiverseIdTemp); existingForeignData =
						foreignDataRepo.findOneBy(ForeignData.class, "foreigndata_multiverseid", multiverseIdTemp);
					}
					if (existingForeignData == null) {
						ForeignData foreignDataEntity = new ForeignData();
						if (foreignData.has("flavorText")) {
							foreignDataEntity.setForeigndataFlavortext(foreignData.get("flavorText").asText());
						}
						if (foreignData.has("multiverseId")) {
							foreignDataEntity.setForeigndataMultiverseid(
									new BigInteger(foreignData.get("multiverseId").asText()));
						} else {
							foreignDataEntity.setForeigndataMultiverseid(multiverseIdTemp);
						}
						if (foreignData.has("name")) {
							foreignDataEntity.setForeigndataName(foreignData.get("name").asText());
						}
						if (foreignData.has("text")) {
							foreignDataEntity.setForeigndataText(foreignData.get("text").asText());
						}

						if (foreignData.has("type")) {
							foreignDataEntity.setForeigndataType(foreignData.get("type").asText());
						} else { // System.out.println(foreignData); } if (foreignData.has("language"))
							{
								Language existingLanguage = languageRepo.findOneBy(Language.class, "language_name",
										foreignData.get("language").asText());
								if (existingLanguage == null) {
									Language languageEntity = new Language();
									languageEntity.setLanguageName(foreignData.get("language").asText());
									languageRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, languageEntity);
									foreignDataEntity.setLanguage(languageEntity);
								} else {
									foreignDataEntity.setLanguage(existingLanguage);
								}
							}
							foreignDataCollection.add(foreignDataEntity);
						}
						cardEntity.setForeignDataCollection(foreignDataCollection);
					}
				}

				FrameVersion existsingFrameVersion = frameVersionRepo.findOneBy(FrameVersion.class, "frameversion_name",
						card.get("frameVersion").asText());
				if (existsingFrameVersion == null) {
					FrameVersion frameVersionEntity = new FrameVersion();
					frameVersionEntity.setFrameversionName(card.get("frameVersion").asText());
					frameVersionRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, frameVersionEntity);
					cardEntity.setFrameVersion(frameVersionEntity);
				} else {
					cardEntity.setFrameVersion(existsingFrameVersion);
				}

				if (card.has("hasFoil") && card.get("hasFoil").asBoolean()) {
					cardEntity.setCardHasfoil(true);
				} else {
					cardEntity.setCardHasfoil(false);
				}

				if (card.has("hasNonFoil") && card.get("hasNonFoil").asBoolean()) {
					cardEntity.setCardHasnonfoil(true);
				} else {
					cardEntity.setCardHasnonfoil(false);
				}

				if (card.has("isFoilOnly") && card.get("isFoilOnly").asBoolean()) {
					cardEntity.setCardIsfoilonly(true);
				} else {
					cardEntity.setCardIsfoilonly(false);
				}

				if (card.has("isOnlineOnly") && card.get("isOnlineOnly").asBoolean()) {
					cardEntity.setCardIsonlineonly(true);
				} else {
					cardEntity.setCardIsonlineonly(false);
				}

				if (card.has("isOversized") && card.get("isOversized").asBoolean()) {
					cardEntity.setCardIsoversized(true);
				} else {
					cardEntity.setCardIsoversized(false);
				}

				if (card.has("isReserved") && card.get("isReserved").asBoolean()) {
					cardEntity.setCardIsreserved(true);
				} else {
					cardEntity.setCardIsreserved(false);
				}

				if (card.has("isTimeshifted") && card.get("isTimeshifted").asBoolean()) {
					cardEntity.setCardIstimeshifted(true);
				} else {
					cardEntity.setCardIstimeshifted(false);
				}

				Layout existingLayout = layoutRepo.findOneBy(Layout.class, "layout_name", card.get("layout").asText());
				if (existingLayout == null) {
					Layout layoutEntity = new Layout();
					layoutEntity.setLayoutName(card.get("layout").asText());
					layoutRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, layoutEntity);
					cardEntity.setLayout(layoutEntity);
				} else {
					cardEntity.setLayout(existingLayout);
				}

				if (card.has("uuid")) {
					cardEntity.setCardUuid(card.get("uuid").asText());
				}

				Card existingCard = cardRepo.findOneBy(cardEntity.getClass(), "card_uuid", cardEntity.getCardUuid());
				if (existingCard == null) {
					cardRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, cardEntity);
				} //
			}
		}
	}
}

//    JsonNode legalities = card.get("legalities");
//	  Iterator<JsonNode> legalityIterator = legalities.elements(); // // while
//	  (legalityIterator.hasNext()) { // JsonNode legality =
//	  legalityIterator.next(); // System.out.println(legality); // Legality
//	  existingLegality = legalityRepo.findOneBy(Legality.class, "legality_name",
//	  legality.get(0)); // // }
