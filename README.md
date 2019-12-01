# requirements
This repository provides a content management system for requirements. It is organised by commodity and each country has a folder within each commodity. Within each commodity + country folder is a file called "readme.md". This file contains the guidance for the country and commodity. This repository provides elements of content authoring, content repository and content publication as shown in the diagram below.

![](https://github.com/michaelglasson/requirements/blob/master/Requirements%20Again.png)

## Overview
The repository - under "db" is structured with a top level of commodities and a lower level of countries. Any folder can have a readme.md file which provides information for that folder. For example, the readme.md file in a folder called "dairy/brazil" should contain the information for Brazil's dairy import requirements.

Both commodities and countries can have sub-folders. For example, the European Union has sub-folders for member countries. If a country or commodity has information in its readme file and it also has a sub-folder, then the information is merged. For example, since Hungary is a member of the EU, any information from Hungary is, by default,intended to be merged with the EU information. Further rules will be developed to allow information to override information from farther up the tree, to be merged (the current default) or to be ignored.




