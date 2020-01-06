package pl.twardy;

import pl.twardy.records.ArticleFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1> BibTex Parser Project</h1>
 * The bibtex parser implements an application that
 * simply parse bibtex file into classes and
 * allows you to find records by author and filter by record name
 *
 * @author Filip Twardy
 * @version 1.0
 * @since 05.01.2020
 */
public class Main {

    /**
     * This is main method communicates with user
     * via simple console questions
     * @param args application console arguments
     * @throws IOException this handles bad file path error
     */
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Hello in BibTex Parser! \n" +
                    "You have to specify path to your BibTex file e. g. /home/user/file \n" +
                    "then you can pass RegEx expression to find authors in given file :)");
        }
        else {

            System.out.println("Here you can pass types of records you need to find in");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String typesToFind = reader.readLine();

            String[] types = typesToFind.split(" ");
            Arrays.stream(types).forEach(System.out::println);


            Record article = ArticleFactory.make();

            Storage storage = new Storage();
            storage.addRecord(article);

            Parser parser = new Parser(args[0]);

            List<Record> rec = parser.getRecordsFromFile();

            if (types.length != 0){
                rec = rec.stream()
                        .filter(g -> Arrays.stream(types)
                        .anyMatch(t -> t.toLowerCase().equals(g.getType().toLowerCase())))
                        .collect(Collectors.toList());
            }

            if (args.length == 2){
                Finder finder = new Finder();
                rec = finder.findByAuthor(rec, args[1]);
            }
            rec.forEach(System.out::println);
        }
    }
}
