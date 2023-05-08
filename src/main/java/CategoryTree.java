
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryTree {

    private TreeNode root;
    private Map<Integer, TreeNode> nodeMap;

    public CategoryTree() {
        root = new TreeNode(0, "ROOT");
        nodeMap = new HashMap<>();
        nodeMap.put(0, root);
    }

    // 트리에 노드를 추가하는 메소드
    public void addNode(int id, String name, int parentId) {
        TreeNode node = new TreeNode(id, name);
        nodeMap.put(id, node);
        TreeNode parent = nodeMap.get(parentId);
        parent.addChild(node);
    }

    // 카테고리 식별자로 해당 카테고리와 하위 카테고리를 모두 반환하는 메소드
    public List<TreeNode> searchById(int id) {
        List<TreeNode> result = new ArrayList<>();
        if (nodeMap.containsKey(id)) {
            TreeNode node = nodeMap.get(id);
            result.add(node);
            result.addAll(node.getAllChildren());
        }
        return result;
    }

    // 카테고리 이름으로 해당 카테고리와 하위 카테고리를 모두 반환하는 메소드
    public List<TreeNode> searchByName(String name) {
        List<TreeNode> result = new ArrayList<>();
        for (TreeNode node : nodeMap.values()) {
            if (node.getName().equals(name)) {
                result.add(node);
                result.addAll(node.getAllChildren());
            }
        }
        return result;
    }

    // 트리를 Json 형태로 변환하는 메소드
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(root);
    }
}

class TreeNode {

    private int id;
    private String name;
    private TreeNode parent;
    private List<TreeNode> children;

    public TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
        this.children = new ArrayList<>();
    }

    // 자식 노드 추가 메소드
    public void addChild(TreeNode node) {
        children.add(node);
        node.setParent(this);
    }

    // 모든 자식 노드를 반환하는 메소드
    public List<TreeNode> getAllChildren() {
        List<TreeNode> result = new ArrayList<>();
        for (TreeNode child : children) {
            result.add(child);
            result.addAll(child.getAllChildren());
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}
