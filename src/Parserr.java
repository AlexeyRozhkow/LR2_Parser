import java.util.ArrayList;
import java.util.List;

public class Parserr {
    private List<Token> tokens = new ArrayList<>();
    private int position = 0;

    boolean lang(List<Token> tokens) {
        boolean lang = false;
        for (Token token : tokens) {
            if (token.getLexeme() != Lexeme.WS) {
                this.tokens.add(token);
            }
        }
        while (this.tokens.size() != position) {
            if (!expr()) {
                System.err.println(" Error: Syntax mistake ");
                System.exit(4);
            } else
                lang = true;
        }
        return lang;
    }

    private boolean expr() {
        boolean expr = false;

        if (init() || assign() || for_loop()) {
            expr = true;
        }
        return expr;
    }

    private boolean init() {
        boolean init = false;
        int old_position = position;

        if (getCurrentTokenLexemeInc() == Lexeme.TYPE) {
            if (assign_op()) {
                if (getCurrentTokenLexemeInc() == Lexeme.SEM) {
                    init = true;
                }
            }
        }
        position = init ? position : old_position;
        return init;
    }

    private boolean assign() {
        boolean assign = false;
        int old_position = position;

        if (assign_op()) {
            if (getCurrentTokenLexemeInc() == Lexeme.SEM) {
                assign = true;
            }
        }
        position = assign ? position : old_position;
        return assign;
    }

    private boolean assign_op() {
        boolean assign_op = false;
        int old_position = position;

        if (getCurrentTokenLexemeInc() == Lexeme.VAR) {
            if (getCurrentTokenLexemeInc() == Lexeme.ASSIGN_OP) {
                if (value()) {
                    assign_op = true;
                }
            }
        }
        position = assign_op ? position : old_position;
        return assign_op;
    }

    private boolean value() {
        boolean value = false;

        if (val()) {
            while (OPval()) {
            }
            value = true;
        }
        return value;
    }

    private boolean OPval() {
        boolean OPval = false;
        int old_position = position;

        if (getCurrentTokenLexemeInc() == Lexeme.OP) {
            if (val()) {
                OPval = true;
            }
        }
        position = OPval ? position : old_position;
        return OPval;
    }

    private boolean val() {
        boolean val = false;

        if (getCurrentTokenLexemeInc() == Lexeme.VAR) {
            return true;
        } else {
            position--;
        }
        if (getCurrentTokenLexemeInc() == Lexeme.DIGIT) {
            return true;
        } else {
            position--;
        }
        if (break_value()) {
            return true;
        }
        return val;
    }

    private boolean break_value() {
        boolean break_value = false;
        int old_position = position;

        if (getCurrentTokenLexemeInc() == Lexeme.L_R_SQU) {
            if (value()) {
                if (getCurrentTokenLexemeInc() == Lexeme.R_R_SQU) {
                    break_value = true;
                }
            }
        }
        position = break_value ? position : old_position;
        return break_value;
    }

    private boolean for_loop() {
        boolean for_loop = false;
        int old_position = position;

        if (getCurrentTokenLexemeInc() == Lexeme.FOR) {
            if (for_expr()) {
                if (for_body()) {
                    for_loop = true;
                }
            }
        }
        position = for_loop ? position : old_position;
        return for_loop;
    }

    private boolean for_body() {
        boolean for_body = false;
        int old_position = position;

        if (getCurrentTokenLexemeInc() == Lexeme.L_F_SQU) {
            while (for_form_square()) {
            }
            if (getCurrentTokenLexemeInc() == Lexeme.R_F_SQU) {
                for_body = true;
            }
        }
        position = for_body ? position : old_position;
        return for_body;
    }

    private boolean for_form_square() {
        boolean for_form_square = false;

        if (init() || assign()) {
            for_form_square = true;
        }
        return for_form_square;
    }

    private boolean for_expr() {
        boolean for_expr = false;
        int old_position = position;

        if (getCurrentTokenLexemeInc() == Lexeme.L_R_SQU) {
            if (start_expr()) {
                if (log_expr()) {
                    if (assign_op()) {
                        if (getCurrentTokenLexemeInc() == Lexeme.R_R_SQU) {
                            for_expr = true;
                        }
                    }
                }
            }
        }
        position = for_expr ? position : old_position;
        return for_expr;
    }

    private boolean log_expr() {
        boolean log_expr = false;
        int old_position = position;

        if (assign_op() || value()) {
            if (getCurrentTokenLexemeInc() == Lexeme.LOG_OP) {
                if (assign_op() || value()) {
                    if (getCurrentTokenLexemeInc() == Lexeme.SEM) {
                        log_expr = true;
                    }
                }
            }
        }
        position = log_expr ? position : old_position;
        return log_expr;
    }

    private boolean start_expr() {
        boolean start_expr = false;

        if (init() || assign()) {
            start_expr = true;
        }
        return start_expr;
    }

    private Lexeme getCurrentTokenLexemeInc() {
        try {
            return tokens.get(position++).getLexeme();
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("Error: Lexeme \"" + Lexeme.TYPE + "\" expected");
            System.exit(3);
        }
        return null;
    }
}
