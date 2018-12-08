package be.bbs.mtgmanager.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.bbs.bbsfx.orm.DatabaseHelper;
import be.bbs.bbsfx.utils.AppHelper;
import be.bbs.mtgmanager.entity.Card;
import be.bbs.mtgmanager.entity.Set;
import be.bbs.mtgmanager.entity.SetType;
import be.bbs.mtgmanager.entity.Type;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityTransaction;

import org.hibernate.Session;

public class JsonLoader {
	

	public static void main(String[] args) throws IOException {
		
		
		
		
		AppHelper helper = new AppHelper();
		
	    File jsonFile = new File("C:\\Users\\boris\\Desktop\\AllSets.json");
	    System.exit(0);
		byte[] jsonData = Files.readAllBytes(jsonFile.toPath());
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode sets = mapper.readTree(jsonData);
		Iterator<JsonNode> iSets = sets.elements();
		Session session = DatabaseHelper.getSession();
		EntityTransaction tx = session.getTransaction();
		
		try {
			tx.begin();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.exit(1);
		}
		
		while (iSets.hasNext()) {
			JsonNode set = iSets.next();
			Set setEntity = new Set();
			
			try {
				int baseSetSize = set.get("baseSetSize").asInt();
				setEntity.setBaseSetSize(baseSetSize);
			} catch (Exception e1) {
			}
			
			try {
				String block = set.get("block").asText();
				setEntity.setBlock(block);
			} catch (Exception e1) {
			}
			
			try {
				String boosterV3 = set.get("boosterV3").asText();
				setEntity.setBoosterV3(boosterV3);
			} catch (Exception e1) {
			}

			try {
				JsonNode cards = set.get("cards");				
			} catch(Exception e) {
				
			}
			
			try {
				String code = set.get("code").asText();
				setEntity.setCode(code);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}
			
			try {
				String meta = set.get("meta").asText();
				setEntity.setMeta(meta);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}

			try {
				String mtgoCode = set.get("mtgoCode").asText();
				setEntity.setMtgoCode(mtgoCode);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}

			try {
				String name = set.get("name").asText();
				setEntity.setName(name);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}

			try {
				String releaseDate = set.get("releaseDate").asText();
				setEntity.setReleaseDate(releaseDate);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}

			try {
				JsonNode tokens = set.get("tokens");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}

			try {
				int totalSetSize = set.get("totalSetSize").asInt();
				setEntity.setTotalSetSize(totalSetSize);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}
			
			try {
				boolean isOnlineOnly = set.get("isOnlineOnly").asBoolean();
				setEntity.setIsOnlineOnly(isOnlineOnly);
			} catch(Exception e) {
			}

			try {
				String type = set.get("type").asText();
				
				try {
					SetType setType = new SetType();
					setType.setName(type);
					setEntity.setSetType(setType);
					session.persist(setType);
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			
			
			
			try {
				session.persist(setEntity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(setEntity.toString());
		}
		
		if (tx.isActive()) { 
		    tx.commit();
		} else {
			tx.rollback();			
		}

		
		session.close();
		
	}
}
