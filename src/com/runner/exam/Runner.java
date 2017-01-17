package com.runner.exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;

public class Runner {

	public static void main(String[] args) {
		ReadJsonAThenFormatTojsonB();
	}

	//Read the File From File/jsonA.txt and translate it to jsonB format
	static void ReadJsonAThenFormatTojsonB() {
		BufferedReader bufferReader = null;
		FileReader jsonFileReader = null;
		String jsonFileLocation = "FILE\\jsonA.txt";
		String sCurrentLine;
		try {

			jsonFileReader = new FileReader(jsonFileLocation);
			bufferReader = new BufferedReader(jsonFileReader);

			bufferReader = new BufferedReader(new FileReader(jsonFileLocation));

			StringBuilder sb = new StringBuilder();

			while ((sCurrentLine = bufferReader.readLine()) != null) {

				sb.append(sCurrentLine);
			}

			FormatToJSONB(sb.toString());

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bufferReader != null)
					bufferReader.close();

				if (jsonFileReader != null)
					jsonFileReader.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

	private static void FormatToJSONB(String stringArrayA) {

		try {

			JSONArray jsonArrayA = new JSONArray(stringArrayA);

			List<String> managerList = new ArrayList<String>();
			List<String> subordinateList = new ArrayList<String>();

			for (int objectCounter = 0; objectCounter < jsonArrayA.length(); objectCounter += 1) {
				managerList.add(jsonArrayA.getJSONObject(objectCounter).getString("manager_name"));
				subordinateList.add(jsonArrayA.getJSONObject(objectCounter).getString("login_name"));
			}

			//This would count myManager list
			Set<String> managerNames = new HashSet<String>(managerList);
			
			//Start to format the json Data
			for (String managers : managerNames) {
				
				System.out.println("\"subordinate\": [");
				int numberOfSubordinate = Collections.frequency(managerList, managers);

				//this should print out the subordinates per manager, but I am printing my name each instance!!!
				
				for (int subordinateCounter = 0; subordinateCounter < numberOfSubordinate; subordinateCounter += 1) {
					if (subordinateCounter < numberOfSubordinate - 1) {
						System.out.println("{" + "marangelo" + subordinateCounter + "},");
					} else {
						System.out.println("{" + "marangelo" + subordinateCounter + "}");
					}

				}
				System.out.println("],");
				System.out.println("\"name\" : \"" + managers + "\"");
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

}
