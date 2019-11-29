package com.mynym.micor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main2 {

	public static void main(String[] args) {
		createFS("commodities.txt", Paths.get("db"), true, "");
	}

	public static void createFS(String sourceFileName, Path base, Boolean needsCountries, String commodity) {
		try (BufferedReader source = new BufferedReader(new FileReader(sourceFileName))) {
			String line;
			int level;
			Path p = base;
			Path path = Paths.get("db\\Templates\\guidance.md");
			String guidance = "";
			try {
				guidance = new String(Files.readAllBytes(path));
			} catch (IOException e) {
				e.printStackTrace();
			}

			while ((line = source.readLine()) != null) {
				level = line.lastIndexOf("\t") + 1;
				String nodeName = line.substring(level);
				guidance = guidance.replace("$COMMODITY$", commodity);
				if (!needsCountries)
					guidance = guidance.replace("$COUNTRY$", nodeName);
				if (level < p.getNameCount() - base.getNameCount()) {
					p = p.subpath(0, base.getNameCount() + level);
				}
				p = p.resolve(nodeName);
				Files.createDirectory(p);
				Files.write(p.resolve("guidance.md"), guidance.getBytes());
				if (needsCountries)
					createFS("countries.txt", p, false, nodeName);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
