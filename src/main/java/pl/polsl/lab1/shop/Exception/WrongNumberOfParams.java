package pl.polsl.lab1.shop.Exception;

/**
 * Wrong number of params exception
 *
 * @author kamil_machulik
 * @version 1.0
 */
public class WrongNumberOfParams extends Exception {
    public WrongNumberOfParams() {
        super("Wrong number of submitted params to main func");
    }
}
