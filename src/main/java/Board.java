import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Board {

    private int id;
    private String categoryName;
    private int parentCategoryId;
    private List<Board> subBoards;

    public Board(int id, String categoryName, int parentCategoryId) {
        this.id = id;
        this.categoryName = categoryName;
        this.parentCategoryId = parentCategoryId;
        this.subBoards = new ArrayList<Board>();
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public List<Board> getSubBoards() {
        return subBoards;
    }

    public void addSubBoard(Board board) {
        subBoards.add(board);
    }

    public static Board createBoardTree(List<Board> boards) {
        Map<Integer, Board> boardMap = new HashMap<Integer, Board>();

        for (Board board : boards) {
            boardMap.put(board.getId(), board);
        }

        Board rootBoard = null;

        for (Board board : boards) {
            int parentCategoryId = board.getParentCategoryId();
            if (parentCategoryId == 0) {
                rootBoard = board;
            } else {
                Board parentBoard = boardMap.get(parentCategoryId);
                if (parentBoard != null) {
                    parentBoard.addSubBoard(board);
                }
            }
        }

        return rootBoard;
    }

    public void printBoardTree(String prefix) {
        System.out.println(prefix + "- " + this.getCategoryName());
        for (Board subBoard : this.getSubBoards()) {
            subBoard.printBoardTree(prefix + "  ");
        }
    }

    public static Board findBoardByName(String categoryName, Board board) {
        if (board.getCategoryName().equals(categoryName)) {
            return board;
        } else {
            for (Board subBoard : board.getSubBoards()) {
                Board foundBoard = findBoardByName(categoryName, subBoard);
                if (foundBoard != null) {
                    return foundBoard;
                }
            }
            return null;
        }
    }

    public static String getJsonText(Board board) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(board);
    }

    public static void main(String[] args) {
        List<Board> boards = new ArrayList<Board>();
        boards.add(new Board(1, "남자", 0));
        boards.add(new Board(2, "첸", 1));
        boards.add(new Board(3, "백현", 1));
        boards.add(new Board(4, "시우민", 1));
        boards.add(new Board(5, "방탄소년단", 0));
        boards.add(new Board(6, "익명게시판", 0));
        boards.add(new Board(7, "뷔", 5));
        boards.add(new Board(8, "여자", 0));
        boards.add(new Board(9, "로제", 8));
        boards.add(new Board(10, "블랙핑크", 8));
        boards.add(new Board(11, "공지사항", 5));
        boards.add(new Board(12, "공지사항", 10));
        boards.add(new Board(13, "공지사항", 8));

        Board.createBoardTree(boards).printBoardTree("");
    }


}