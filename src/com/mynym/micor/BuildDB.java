package com.mynym.micor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BuildDB {

	public static void main(String[] args) {
		Path path = Paths.get("db\\Templates\\readme.md");
		String readme = "";
		try {
			readme = new String(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		createCommodities("commodities.txt", Paths.get("db"), readme);
	}

	public static void createCommodities(String sourceFileName, Path base, String guidance) {
		try (BufferedReader source = new BufferedReader(new FileReader(sourceFileName))) {
			String line;
			int level;
			Path p = base;
			while ((line = source.readLine()) != null) {
				level = line.lastIndexOf("\t") + 1;
				String nodeName = line.substring(level);
				if (level < p.getNameCount() - base.getNameCount()) {
					p = p.subpath(0, base.getNameCount() + level);
				}
				p = p.resolve(nodeName);
				Files.createDirectory(p);
				String readmeCopy = guidance.replace("$COMMODITY$", nodeName.toUpperCase());
				String readmeCommodity = readmeCopy;
				readmeCopy = readmeCopy.replace("TO $COUNTRY$", "");
				Files.write(p.resolve("readme.md"), readmeCopy.getBytes());
				createCountries("countries.txt", p, readmeCommodity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void createCountries(String sourceFileName, Path base, String readme) {
		try (BufferedReader source = new BufferedReader(new FileReader(sourceFileName))) {
			String line;
			int level;
			Path p = base;
			while ((line = source.readLine()) != null) {
				level = line.lastIndexOf("\t") + 1;
				String nodeName = line.substring(level);
				if (level < p.getNameCount() - base.getNameCount()) {
					p = p.subpath(0, base.getNameCount() + level);
				}
				p = p.resolve(nodeName);
				Files.createDirectory(p);
				String readmeCopy = readme.replace("$COUNTRY$", nodeName.toUpperCase());
				Files.write(p.resolve("readme.md"), readmeCopy.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
