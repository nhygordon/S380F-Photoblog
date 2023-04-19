package hkmu.comps380f.exception;

public class BlogNotFound extends Exception {
    public BlogNotFound(long id) {
        super("Blog " + id + " does not exist.");
    }
}

