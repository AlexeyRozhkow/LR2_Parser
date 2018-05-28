import javax.swing.text.html.parser.Parser;
import java.util.List;

public class LR3 {
    public static void main(String[] args) {

        Lexer lexer = new Lexer();
        Parserr parser = new Parserr();
        String input = "for(int i=0 ; i!=6 ; i=i+1 ){k=(k+1)*((e+r)*t);}";

        List<Token> tokens = lexer.recognize(input);

        /*System.out.println('\n');
        for (Token token : tokens) {
            System.out.println(token);
        }*/
        System.out.println('\n');
        System.out.println("[ " + input + " ]");
        System.out.println('\n');
        System.out.println(parser.lang(tokens));
    }

}
