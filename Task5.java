import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Symbol {
    private char character;

    public Symbol(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}

class Punctuation extends Symbol {
    public Punctuation(char character) {
        super(character);
    }
}

class Word {
    private List<Symbol> symbols;

    public Word(String word) {
        symbols = new ArrayList<>();
        for (char c : word.toCharArray()) {
            symbols.add(new Symbol(c));
        }
    }

    public String getWord() {
        StringBuilder sb = new StringBuilder();
        for (Symbol s : symbols) {
            sb.append(s.getCharacter());
        }
        return sb.toString();
    }
}

class Sentence {
    private List<Object> components; 

    public Sentence(String sentence) {
        components = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(sentence, " ,.!?;:()-", true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (",.!?;:()-".contains(token)) {
                components.add(new Punctuation(token.charAt(0)));
            } else if (!token.trim().isEmpty()) {
                components.add(new Word(token));
            }
        }
    }

    public List<Object> getComponents() {
        return components;
    }

    public String getSentence() {
        StringBuilder sb = new StringBuilder();
        for (Object component : components) {
            if (component instanceof Word) {
                sb.append(((Word) component).getWord()).append(" ");
            } else if (component instanceof Punctuation) {
                sb.append(((Punctuation) component).getCharacter());
            }
        }
        return sb.toString().trim();
    }
}

class Paragraph {
    private List<Sentence> sentences;

    public Paragraph(String paragraph) {
        sentences = new ArrayList<>();
        String[] sentenceArray = paragraph.split("(?<=[.!?])\\s+");
        for (String sentence : sentenceArray) {
            sentences.add(new Sentence(sentence));
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public String getFormattedParagraph(int lineWidth) {
        StringBuilder formattedParagraph = new StringBuilder();
        for (Sentence sentence : sentences) {
            String formattedSentence = justifyText(sentence.getSentence(), lineWidth);
            formattedParagraph.append(formattedSentence).append("\n");
        }
        return formattedParagraph.toString();
    }

    private String justifyText(String text, int lineWidth) {
        String[] words = text.split("\\s+");
        int totalLength = 0;
        for (String word : words) {
            totalLength += word.length();
        }

        int spacesNeeded = lineWidth - totalLength;
        int gapsBetweenWords = words.length - 1;

        StringBuilder justifiedText = new StringBuilder();
        if (gapsBetweenWords > 0) {
            int spacesPerGap = spacesNeeded / gapsBetweenWords;
            int extraSpaces = spacesNeeded % gapsBetweenWords;

            for (int i = 0; i < words.length - 1; i++) {
                justifiedText.append(words[i]);
                for (int j = 0; j < spacesPerGap; j++) {
                    justifiedText.append(" ");
                }
                if (extraSpaces > 0) {
                    justifiedText.append(" ");
                    extraSpaces--;
                }
            }
            justifiedText.append(words[words.length - 1]);
        } else {
            justifiedText.append(text);
        }

        return justifiedText.toString();
    }
}

class Listing {
    private List<Paragraph> paragraphs;

    public Listing(String text) {
        paragraphs = new ArrayList<>();
        String[] paragraphArray = text.split("\n\n");
        for (String paragraph : paragraphArray) {
            paragraphs.add(new Paragraph(paragraph));
        }
    }

    public void printFormattedText(int lineWidth) {
        for (Paragraph paragraph : paragraphs) {
            System.out.println(paragraph.getFormattedParagraph(lineWidth));
        }
    }
}

public class TextFormatter {
    public static void main(String[] args) {
        String text = "Java is a popular programming language. It is widely used for building enterprise applications.\n\n"
                + "Java has many features. Some of them are platform independence and security.";

        Listing listing = new Listing(text);
        int lineWidth = 50;
        listing.printFormattedText(lineWidth);
    }
}
