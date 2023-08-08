import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.util.*;

public class NLPProcessor {
  private StanfordCoreNLP pipeline;

  public NLPProcessor() {
    // Set logging level to SEVERE to suppress output to command line
    java.util.logging.Logger.getLogger("edu.stanford.nlp").setLevel(java.util.logging.Level.SEVERE);

    // Create properties and pipeline
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,natlog,openie");
    pipeline = new StanfordCoreNLP(props);
  }

  public List<String> extractNounPhrases(ArrayList<String> commentsList) {
    List<String> nounPhrases = new ArrayList<>();
    for (String comment : commentsList) {
      // Annotate the comment
      Annotation document = new Annotation(comment);
      pipeline.annotate(document);

      // Iterate over sentences
      for (CoreMap sentence : document.get(SentencesAnnotation.class)) {
        String prevToken = "";
        String nounPhrase = "";
        for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
          String pos = token.get(PartOfSpeechAnnotation.class);
          String word = token.get(TextAnnotation.class);
          if (pos.equals("JJ")) {
            prevToken = word;
          } else if (pos.startsWith("NN")) {
            if (!prevToken.isEmpty()) {
              nounPhrase = prevToken + " " + word;
              nounPhrases.add(nounPhrase);
              prevToken = "";
            } else if (!nounPhrase.isEmpty()) {
              nounPhrase += " " + word;
              nounPhrases.set(nounPhrases.size() - 1, nounPhrase);
            } else {
              nounPhrase = word;
              nounPhrases.add(nounPhrase);
            }
          } else {
            prevToken = "";
            nounPhrase = "";
          }
        }
      }
    }
    return nounPhrases;
  }
}
