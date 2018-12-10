package be.bbs.bbsfx.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.bbs.bbsfx.orm.DatabaseHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;

public class JsonLoader {

	public static void main(String[] args) throws IOException {

		AppHelper helper = new AppHelper();

		File jsonFile = new File("C:\\Users\\boris\\Desktop\\AllSets.json");
		byte[] jsonData = Files.readAllBytes(jsonFile.toPath());

		ObjectMapper mapper = new ObjectMapper();
		JsonNode expansions = mapper.readTree(jsonData);
		Iterator<JsonNode> expansionIterator = expansions.elements();
		Session session = DatabaseHelper.getSession();
		EntityTransaction tx = session.getTransaction();
		session.clear();

		try {
			tx.begin();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.exit(1);
		}
		// System.exit(0);
		while (expansionIterator.hasNext()) {
			JsonNode expansion = expansionIterator.next();
			System.out.println(expansion.get("code").asText());


		}
/*
		if (tx.isActive()) {
			tx.commit();
		} else {
			tx.rollback();
		}
*/
		session.close();

	}
}
