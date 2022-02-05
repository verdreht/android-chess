package net.nightcodes.androidchess.game.logic.board;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.nightcodes.androidchess.Constants;
import net.nightcodes.androidchess.game.entity.Bishop;
import net.nightcodes.androidchess.game.entity.King;
import net.nightcodes.androidchess.game.entity.Knight;
import net.nightcodes.androidchess.game.entity.Pawn;
import net.nightcodes.androidchess.game.entity.Queen;
import net.nightcodes.androidchess.game.entity.Rook;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.game.logic.movement.exception.IllegalLocationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final Field[][] board = new Field[8][8];

    private EntityColor currentTurn;

    public static Board getInstance() {
        return new Board();
    }

    public void setup() throws IllegalLocationException {
        for(int i = 0; i < board.length; i++) {
            for(int x = 0; x < board[i].length; x++) {
                board[i][x] = new Field(new Location(i + 1, x + 1));
            }
        }
        initEntities();
    }

    private void initEntities() {

        getField(1, 1).setEntity(new Rook().setEntityColor(EntityColor.WHITE));
        getField(1, 2).setEntity(new Knight().setEntityColor(EntityColor.WHITE));
        getField(1, 3).setEntity(new Bishop().setEntityColor(EntityColor.WHITE));
        getField(1, 4).setEntity(new King().setEntityColor(EntityColor.WHITE));
        getField(1, 5).setEntity(new Queen().setEntityColor(EntityColor.WHITE));
        getField(1, 6).setEntity(new Bishop().setEntityColor(EntityColor.WHITE));
        getField(1, 7).setEntity(new Knight().setEntityColor(EntityColor.WHITE));
        getField(1, 8).setEntity(new Rook().setEntityColor(EntityColor.WHITE));

        for(int i = 1; i <= 8; i++) {
            getField(2, i).setEntity(new Pawn().setEntityColor(EntityColor.WHITE));
        }

        getField(8, 1).setEntity(new Rook().setEntityColor(EntityColor.BLACK));
        getField(8, 2).setEntity(new Knight().setEntityColor(EntityColor.BLACK));
        getField(8, 3).setEntity(new Bishop().setEntityColor(EntityColor.BLACK));
        getField(8, 4).setEntity(new King().setEntityColor(EntityColor.BLACK));
        getField(8, 5).setEntity(new Queen().setEntityColor(EntityColor.BLACK));
        getField(8, 6).setEntity(new Bishop().setEntityColor(EntityColor.BLACK));
        getField(8, 7).setEntity(new Knight().setEntityColor(EntityColor.BLACK));
        getField(8, 8).setEntity(new Rook().setEntityColor(EntityColor.BLACK));

        for(int i = 1; i <= 8; i++) {
            getField(7, i).setEntity(new Pawn().setEntityColor(EntityColor.BLACK));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isAllowedToMove(IEntity<?> entity, Field currentLocation, Field nextMoveLocation) {
        if (!entity.canMove(currentLocation, nextMoveLocation, Constants.getBoard()).equals(MoveResult.NOT_PERMITTED)) {
            return true;
        } else {
            return false;
        }
    }

    public void move(IEntity entity, Field currentLocation, Field nextMoveLocation) {
        nextMoveLocation.setEntity(entity);
        currentLocation.setEntity(null);
        Constants.getBoardEventManager().call(this);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Field[] x : board)
        {
            for (Field y : x)
            {
                builder.append(y).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(toJson());
        return builder.toString();
    }

    public void addFieldToBoard(Field field) {
        if (field != null) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == null) {
                        board[i][j] = field;
                        return;
                    }
                }
            }
        }
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("current_turn", (currentTurn != null ? currentTurn.name() : "UNKNOWN"));
        JsonArray row2 = new JsonArray();
        for(int i = 0; i < board.length; i++) {
            JsonArray row = new JsonArray();
            for(int x = 0; x < board[i].length; x++) {
                Field field = getField(i + 1, x + 1);
                JsonObject fieldData = (field != null ? field.toJson() : null);
                if(fieldData != null) row.add(fieldData);
            }
            row2.add(row);
        }
        json.add("fields", row2);
        return json;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Board fromJson(JsonObject json) {
        Board board = Board.getInstance();
        if (json.get("current_turn").getAsString().equals("WHITE")) {
            Constants.setIsServerOnTurn(true);
        } else if (json.get("current_turn").getAsString().equals("BLACK")) {
            Constants.setIsServerOnTurn(false);
        }
        JsonArray fields = json.get("fields").getAsJsonArray();
        fields.forEach(row -> {
            JsonArray column = row.getAsJsonArray();
            column.forEach(entity -> {

                if (!entity.getAsJsonObject().has("entity_type")) {
                    Log.e("EntityNull", "hansi du deppada");
                }

                String entityType = entity.getAsJsonObject().get("entity_type").getAsString();
                EntityColor entityColor = EntityColor.valueOf(entity.getAsJsonObject().get("entity_color").getAsString());
                JsonObject locationAsJson = (JsonObject) entity.getAsJsonObject().get("location");
                int locationX = Integer.parseInt(locationAsJson.get("pos_x").getAsString());
                int locationY = Integer.parseInt(locationAsJson.get("pos_y").getAsString());

                IEntity newEntity = null;
                switch (entityType) {
                    case "Rook":
                        newEntity = new Rook();
                        newEntity.setEntityColor(entityColor);
                        break;
                    case "Queen":
                        newEntity = new Queen();
                        newEntity.setEntityColor(entityColor);
                        break;
                    case "King":
                        newEntity = new King();
                        newEntity.setEntityColor(entityColor);
                        break;
                    case "Knight":
                        newEntity = new Knight();
                        newEntity.setEntityColor(entityColor);
                        break;
                    case "Bishop":
                        newEntity = new Bishop();
                        newEntity.setEntityColor(entityColor);
                        break;
                    case "Pawn":
                        newEntity = new Pawn();
                        newEntity.setEntityColor(entityColor);
                        break;
                    default:
                        break;
                }

                Field newField = null;

                if (newEntity != null) {
                    try {
                        newField = new Field(new Location(locationX, locationY));
                    } catch (IllegalLocationException e) {
                        e.printStackTrace();
                    }
                    newField.setEntity(newEntity);
                }

                board.addFieldToBoard(newField);
                System.out.println("");
            });
        });


        return board;
    }

    public List<Field> getAllFieldsAsList() {
        List<Field> allFields = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            allFields.addAll(Arrays.asList(board[i]));
        }
        return allFields;
    }

    public Field getField(int x, int y) {
        return board[x - 1][y - 1];
    }

    public int getXCoordinates(Field field) {
        return field.getFieldLocation().getX();
    }

    public int getYCoodinates(Field field) {
        return field.getFieldLocation().getY();
    }

}
