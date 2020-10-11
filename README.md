# Console WebCrawler

Technologies: 
-Java 11
-jsoup - parsing text;
-openCSV - write to file;
-apache commons (StringUtils, NumericUtils)- small inserts;
-jUnit - testing;

WebCrawler traverses websites following predefined link depth (8 by default) and max visited pages limit (10000 by default). 
Web crawler starts from predefined URL (seed) and follows links found to dive deeper. 
The main purpose of this crawler to detect the presence of some terms on the page and collect statistics, e.g.
Seed URL, link depth and max visited pages defines in console dialog menu.

After work provides 2 files: "AllResults.csv" and "BestResults.csv" which are saved in application folder.

AllResults.csv contains results as: 
Link| Number of results | Total
e.g.
https://en.wikipedia.org/wiki/Elon_Musk 208 641 9 0 858

BestResults.csv results as: 
Link|Total
e.g.
https://en.wikipedia.org/wiki/Elon_Musk 858

NOTICE: Writing in the file "AllResults.csv" proceed when "Level" will be fully crawled.
Writing in the file "BestResults.csv" proceed when goal will be reached.

You can compile the app with command "mvn compile" in application folder.

Starts with java -jar WebCrawler-2.0-jar-with-dependencies.jar command in console.


