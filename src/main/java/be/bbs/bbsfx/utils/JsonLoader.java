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
	
	public static void load() throws IOException {
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
		
		jsonFile = new File("C:\\Users\\boris\\Desktop\\AllSets.json");
		jsonData = Files.readAllBytes(jsonFile.toPath());
		
		mapper = new ObjectMapper();
		expansions = mapper.readTree(jsonData);
		
		while (expansions.elements().hasNext()) {
			JsonNode expansion = expansions.elements().next();
			Expansion expansionEntity = new Expansion();
			
			ExpansionType existingExpansionTypeEntity = expansionTypeRepo.findOneBy(ExpansionType.class, "expansionType_name", expansion.get("type").asText());
			if (existingExpansionTypeEntity == null) {
				ExpansionType expansionTypeEntity = new ExpansionType();
				expansionTypeEntity.setExpansiontypeName(expansion.get("type").asText());
				expansionTypeEntity.setExpansiontypeLabel(expansion.get("type").asText().substring(0, 1).toUpperCase() + expansion.get("type").asText().substring(1).replaceAll("_", " "));
				expansionTypeRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, expansionTypeEntity);
				expansionEntity.setExpansionType(expansionTypeEntity);
				System.out.println("SAVED!!!!!!!!!!!!!!!!");
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
						}else {
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
				if (expansion.get("isOnlineOnly").isMissingNode() || expansion.get("isOnlineOnly").asBoolean() == false) {
					expansionEntity.setExpansionIsonlineonly(0);
				} else {
					expansionEntity.setExpansionIsonlineonly(1);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				expansionEntity.setExpansionReleasedate(expansion.get("releaseDate").asText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				expansionEntity.setExpansionTotalsetsize(expansion.get("totalSetSize").bigIntegerValue());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (expansionRepo.findOneBy(Expansion.class, "expansion_code", expansionEntity.getExpansionCode()) != null) {
				System.out.println("Expansion already exists");
			} else {
				expansionRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, expansionEntity);				
			}
			
			loadCards(expansion);
		}
	}
	private static void loadCards(JsonNode expansion) throws IOException {
		JsonNode cards = expansion.get("cards");
		ArrayList<Card> cardCollection = new ArrayList<Card>();

		while (cards.elements().hasNext()) {
			JsonNode card = cards.elements().next();
			Card cardEntity = new Card();
			
			Artist existingArtist = artistRepo.findOneBy(Artist.class, "artist_name", card.get("artist").asText());
			if (existingArtist.equals(null)) {
				Artist artistEntity = new Artist();
				artistEntity.setArtistName(card.get("artist").asText());
				artistRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, artistEntity);
			} else {
				cardEntity.setArtist(existingArtist);
			}
			
			BorderColor existingBorderColor = borderColorRepo.findOneBy(BorderColor.class, "bordercolor_name", card.get("borderColor").asText());
			if (existingBorderColor.equals(null)) {
				BorderColor borderColorEntity = new BorderColor();
				borderColorEntity.setBordrecolorName(card.get("borderColor").asText());
				borderColorRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, borderColorEntity);
			} else {
				cardEntity.setBorderColor(existingBorderColor);
			}
			
			JsonNode colors = card.get("colors");
			ArrayList<Color> colorCollection = new ArrayList<Color>();
			while (colors.elements().hasNext()) {
				JsonNode color = colors.elements().next();
				Color existingColorEntity = colorRepo.findOneBy(Color.class, "color_name", color.asText()); 
				if (existingColorEntity.equals(null)) {
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
			
			cardEntity.setCardFaceconvertedmanacost(card.get("faceConvertedManaCost").asDouble());
			
			cardEntity.setCardFlavortext(card.get("flavorText").asText());
			
			JsonNode foreignDatas = card.get("foreignData");
			while (foreignDatas.elements().hasNext()) {
				JsonNode foreignData = foreignDatas.elements().next();
				
				
				ForeignData existingForeignData = foreignDataRepo.findOneBy(ForeignData.class, "foreigndata_multiverseid", foreignData.get("multiverseid").asText());
				if (existingForeignData.equals(null)) {
					ForeignData foreignDataEntity = new ForeignData();
					foreignDataEntity.setForeigndataFlavortext(foreignData.get("flavorText").asText());
					foreignDataEntity.setForeigndataMultiverseid(new BigInteger(foreignData.get("multiverseid").asText()));
					foreignDataEntity.setForeigndataName(foreignData.get("name").asText());
					foreignDataEntity.setForeigndataText(foreignData.get("text").asText());
					foreignDataEntity.setForeigndataType(foreignData.get("type").asText());
					Language existingLanguage = languageRepo.findOneBy(Language.class, "language_name", foreignData.get("language").asText());
					if (existingLanguage.equals(null)) {
						Language languageEntity = new Language();
						languageEntity.setLanguageName(foreignData.get("language").asText());
						languageRepo.execute(GenericRepositoryInterface.SAVE_OR_UPDATE, languageEntity);
						foreignDataEntity.setLanguage(languageEntity);
					} else {
						foreignDataEntity.setLanguage(existingLanguage);
					}
				}
			}
		}
	}
}
