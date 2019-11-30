package com.mynym.micor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		Path path = Paths.get("db\\Templates\\guidance.md");
		String guidance = "";
		try {
			guidance = new String(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		createCommodities("commodities.txt", Paths.get("db"), guidance);
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
				String guidanceCopy = guidance.replace("$COMMODITY$", nodeName.toUpperCase());
				String guidanceCommodity = guidanceCopy;
				guidanceCopy = guidanceCopy.replace("TO $COUNTRY$", "");
				Files.write(p.resolve("readme.md"), guidanceCopy.getBytes());
				createCountries("countries.txt", p, guidanceCommodity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void createCountries(String sourceFileName, Path base, String guidance) {
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
				String guidanceCopy = guidance.replace("$COUNTRY$", nodeName.toUpperCase());
				Files.write(p.resolve("readme.md"), guidanceCopy.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
