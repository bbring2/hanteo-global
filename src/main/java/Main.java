import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        CategoryTree tree = new CategoryTree();
        tree.addNode(1, "남자", 1);
    }
}
