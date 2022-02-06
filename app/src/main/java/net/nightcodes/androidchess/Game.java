package net.nightcodes.androidchess;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import net.nightcodes.androidchess.game.entity.Bishop;
import net.nightcodes.androidchess.game.entity.King;
import net.nightcodes.androidchess.game.entity.Knight;
import net.nightcodes.androidchess.game.entity.Pawn;
import net.nightcodes.androidchess.game.entity.Queen;
import net.nightcodes.androidchess.game.entity.Rook;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.entity.base.ImageAssetType;
import net.nightcodes.androidchess.game.logic.board.Board;
import net.nightcodes.androidchess.game.logic.board.EntityColor;
import net.nightcodes.androidchess.game.logic.board.Field;
import net.nightcodes.androidchess.server.Server;
import net.nightcodes.androidchess.server.broadcast.BroadcastSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Game extends AppCompatActivity implements View.OnClickListener {

    Set<IEntity> imageAssets = new HashSet<>();
    Set<IEntity> blackImageAssets = new HashSet<>();
    Set<IEntity> whiteImageAssets = new HashSet<>();

    //initialize fields -- START
    private Button field_a1;
    private Button field_a2;
    private Button field_a3;
    private Button field_a4;
    private Button field_a5;
    private Button field_a6;
    private Button field_a7;
    private Button field_a8;
    private Button field_b1;
    private Button field_b2;
    private Button field_b3;
    private Button field_b4;
    private Button field_b5;
    private Button field_b6;
    private Button field_b7;
    private Button field_b8;
    private Button field_c1;
    private Button field_c2;
    private Button field_c3;
    private Button field_c4;
    private Button field_c5;
    private Button field_c6;
    private Button field_c7;
    private Button field_c8;
    private Button field_d1;
    private Button field_d2;
    private Button field_d3;
    private Button field_d4;
    private Button field_d5;
    private Button field_d6;
    private Button field_d7;
    private Button field_d8;
    private Button field_e1;
    private Button field_e2;
    private Button field_e3;
    private Button field_e4;
    private Button field_e5;
    private Button field_e6;
    private Button field_e7;
    private Button field_e8;
    private Button field_f1;
    private Button field_f2;
    private Button field_f3;
    private Button field_f4;
    private Button field_f5;
    private Button field_f6;
    private Button field_f7;
    private Button field_f8;
    private Button field_g1;
    private Button field_g2;
    private Button field_g3;
    private Button field_g4;
    private Button field_g5;
    private Button field_g6;
    private Button field_g7;
    private Button field_g8;
    private Button field_h1;
    private Button field_h2;
    private Button field_h3;
    private Button field_h4;
    private Button field_h5;
    private Button field_h6;
    private Button field_h7;
    private Button field_h8;
    //initialize fields -- END
    private List<Button> fieldButtonList = new ArrayList<>();
    private List<Button> whiteFieldButtonList = new ArrayList<>();
    private List<Button> blackFieldButtonList = new ArrayList<>();
    //all Fields from the 2D Board
    private List<Field> fieldList = new ArrayList<>();
    private List<Field> tempFieldList = new ArrayList<>();

    private Board tempBoardForUI;
    private Board tempBoard;

    //initialize Map of fields KEY: id of the buttons, VALUE: Field that matches the buttonID(KEY)
    private Map<Integer, Field> fieldMap = new TreeMap<>();

    private Drawable defaultFieldWhite;
    private Drawable defaultFieldBlack;

    private boolean isFirstClick = true;
    //Buttons
    private Button firstClickedField;
    private Button secondClickedField;
    //Fields for 2D Board
    private Field firstSelectedField;
    private Field secondSelectedField;
    private IEntity movingEntity;

    private Thread serverThread;
    private BroadcastSender broadcast;

    private Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        //setup tempBoards
        tempBoard = Constants.getBoard();

        //setting default fields
        defaultFieldWhite = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.entity_null_white);
        defaultFieldBlack = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.entity_null_black);
        //instancing board!!

        //set button IDs -- START
        field_a1 = findViewById(R.id.field_a1);
        field_a2 = findViewById(R.id.field_a2);
        field_a3 = findViewById(R.id.field_a3);
        field_a4 = findViewById(R.id.field_a4);
        field_a5 = findViewById(R.id.field_a5);
        field_a6 = findViewById(R.id.field_a6);
        field_a7 = findViewById(R.id.field_a7);
        field_a8 = findViewById(R.id.field_a8);
        field_b1 = findViewById(R.id.field_b1);
        field_b2 = findViewById(R.id.field_b2);
        field_b3 = findViewById(R.id.field_b3);
        field_b4 = findViewById(R.id.field_b4);
        field_b5 = findViewById(R.id.field_b5);
        field_b6 = findViewById(R.id.field_b6);
        field_b7 = findViewById(R.id.field_b7);
        field_b8 = findViewById(R.id.field_b8);
        field_c1 = findViewById(R.id.field_c1);
        field_c2 = findViewById(R.id.field_c2);
        field_c3 = findViewById(R.id.field_c3);
        field_c4 = findViewById(R.id.field_c4);
        field_c5 = findViewById(R.id.field_c5);
        field_c6 = findViewById(R.id.field_c6);
        field_c7 = findViewById(R.id.field_c7);
        field_c8 = findViewById(R.id.field_c8);
        field_d1 = findViewById(R.id.field_d1);
        field_d2 = findViewById(R.id.field_d2);
        field_d3 = findViewById(R.id.field_d3);
        field_d4 = findViewById(R.id.field_d4);
        field_d5 = findViewById(R.id.field_d5);
        field_d6 = findViewById(R.id.field_d6);
        field_d7 = findViewById(R.id.field_d7);
        field_d8 = findViewById(R.id.field_d8);
        field_e1 = findViewById(R.id.field_e1);
        field_e2 = findViewById(R.id.field_e2);
        field_e3 = findViewById(R.id.field_e3);
        field_e4 = findViewById(R.id.field_e4);
        field_e5 = findViewById(R.id.field_e5);
        field_e6 = findViewById(R.id.field_e6);
        field_e7 = findViewById(R.id.field_e7);
        field_e8 = findViewById(R.id.field_e8);
        field_f1 = findViewById(R.id.field_f1);
        field_f2 = findViewById(R.id.field_f2);
        field_f3 = findViewById(R.id.field_f3);
        field_f4 = findViewById(R.id.field_f4);
        field_f5 = findViewById(R.id.field_f5);
        field_f6 = findViewById(R.id.field_f6);
        field_f7 = findViewById(R.id.field_f7);
        field_f8 = findViewById(R.id.field_f8);
        field_g1 = findViewById(R.id.field_g1);
        field_g2 = findViewById(R.id.field_g2);
        field_g3 = findViewById(R.id.field_g3);
        field_g4 = findViewById(R.id.field_g4);
        field_g5 = findViewById(R.id.field_g5);
        field_g6 = findViewById(R.id.field_g6);
        field_g7 = findViewById(R.id.field_g7);
        field_g8 = findViewById(R.id.field_g8);
        field_h1 = findViewById(R.id.field_h1);
        field_h2 = findViewById(R.id.field_h2);
        field_h3 = findViewById(R.id.field_h3);
        field_h4 = findViewById(R.id.field_h4);
        field_h5 = findViewById(R.id.field_h5);
        field_h6 = findViewById(R.id.field_h6);
        field_h7 = findViewById(R.id.field_h7);
        field_h8 = findViewById(R.id.field_h8);
        //set button IDs -- END

        //add Buttons to List -- START
        this.fieldButtonList.addAll(Arrays.asList(
                field_a1, field_a2, field_a3, field_a4, field_a5, field_a6, field_a7, field_a8,
                field_b1, field_b2, field_b3, field_b4, field_b5, field_b6, field_b7, field_b8,
                field_c1, field_c2, field_c3, field_c4, field_c5, field_c6, field_c7, field_c8,
                field_d1, field_d2, field_d3, field_d4, field_d5, field_d6, field_d7, field_d8,
                field_e1, field_e2, field_e3, field_e4, field_e5, field_e6, field_e7, field_e8,
                field_f1, field_f2, field_f3, field_f4, field_f5, field_f6, field_f7, field_f8,
                field_g1, field_g2, field_g3, field_g4, field_g5, field_g6, field_g7, field_g8,
                field_h1, field_h2, field_h3, field_h4, field_h5, field_h6, field_h7, field_h8
        ));

        this.blackFieldButtonList.addAll(Arrays.asList(
                field_a1, field_a3, field_a5, field_a7,
                field_b2, field_b4, field_b6, field_b8,
                field_c1, field_c3, field_c5, field_c7,
                field_d2, field_d4, field_d6, field_d8,
                field_e1, field_e3, field_e5, field_e7,
                field_f2, field_f4, field_f6, field_f8,
                field_g1, field_g3, field_g5, field_g7,
                field_h2, field_h4, field_h6, field_h8
        ));

        this.whiteFieldButtonList.addAll(Arrays.asList(
                field_a2, field_a4, field_a6, field_a8,
                field_b1, field_b3, field_b5, field_b7,
                field_c2, field_c4, field_c6, field_c8,
                field_d1, field_d3, field_d5, field_d7,
                field_e2, field_e4, field_e6, field_e8,
                field_f1, field_f3, field_f5, field_f7,
                field_g2, field_g4, field_g6, field_g8,
                field_h1, field_h3, field_h5, field_h7
        ));
        //add Buttons to List -- END
        setAllImageAssets();
        setOnClickListenerForAllFields(this.fieldButtonList);

        setupFieldList();
        setupFieldMap();
    }

    public Thread getServerThread() {
        return serverThread;
    }

    public BroadcastSender getBroadcast() {
        return broadcast;
    }

    public Server getServer() {
        return server;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
//        if (Constants.isRefreshUI()) {
//            tempBoardForUI = Constants.getTempBoardForUI();
//            deserializeBoardToUI();
//            Constants.setRefreshUI(false);
//        }
//        if (!Constants.isMoveLock()) {
            if (view instanceof Button) {
                if (isFirstClick) {
                    if (isFieldExisting(view.getId(), fieldButtonList)) {
                        this.firstClickedField = getButtonById(view.getId());
                        if (imageCollectionContainsImageAsset(firstClickedField.getBackground(), imageAssets)) {
                            this.firstSelectedField = getClickedField(firstClickedField.getId());
                            movingEntity = this.firstSelectedField.getFieldEntity();
                            isFirstClick = false;
                        }
                    }

                } else {
                    if (isFieldExisting(view.getId(), fieldButtonList)) {
                        this.secondClickedField = findViewById(view.getId());

                        this.secondSelectedField = getClickedField(secondClickedField.getId());
                        if (Constants.getBoard().isAllowedToMove(movingEntity, firstSelectedField, secondSelectedField)) {
                            Constants.getBoard().move(movingEntity, firstSelectedField, secondSelectedField);
                            Constants.setMoveLock(true);
                            Drawable newEntityIcon = findEntityDrawableForCurrentMove(firstClickedField.getBackground());
                            firstClickedField.setBackground(setFieldAfterMove());
                            secondClickedField.setBackground(newEntityIcon);
                        }

                        //resetting clickValue to make next move
                        isFirstClick = true;
                    }
                }
            }
//        }
    }

    public Field getFirstSelectedField() {
        return firstSelectedField;
    }

    public Field getSecondSelectedField() {
        return secondSelectedField;
    }

    private Field getClickedField(int buttonId) {
        Field clickedField = null;
        for (Map.Entry<Integer, Field> fieldEntry : fieldMap.entrySet()) {
            if (fieldEntry.getKey().equals(buttonId)) {
                clickedField = fieldEntry.getValue();
            }
        }
        return clickedField;
    }

    private Drawable findEntityDrawableForCurrentMove(Drawable entityDrawable) {
        Drawable result = null;
        IEntity entityType = null;

        if (entityDrawable == null) return result;

        entityType = getEntityTypeFromDrawable(entityDrawable, imageAssets);
        if (secondClickedField != null) {
            if ((isWhiteField(firstClickedField)) &&
                    (isWhiteField(secondClickedField))) {
                //Entity moves onto another white field (same background)
                result = entityDrawable;
            } else if ((!isWhiteField(firstClickedField)) &&
                    (!isWhiteField(secondClickedField))) {
                //Entity moves onto another black field (same background)
                result = entityDrawable;
            } else if ((isWhiteField(firstClickedField)) &&
                    (!isWhiteField(secondClickedField)) &&
                    (isEntityWhite(entityDrawable))) {
                //White Entity moves from a white field to a black field
                result = findImageAsset(entityType, ImageAssetType.WHITE_DARK, imageAssets);
            } else if ((!isWhiteField(firstClickedField)) &&
                    (isWhiteField(secondClickedField)) &&
                    (isEntityWhite(entityDrawable))) {
                //White Entity moves from a black field to a white field
                result = findImageAsset(entityType, ImageAssetType.WHITE_BRIGHT, imageAssets);
            } else if ((isWhiteField(firstClickedField)) &&
                    (!isWhiteField(secondClickedField)) &&
                    (!isEntityWhite(entityDrawable))) {
                //Black Entity moves from a white field to a black field
                result = findImageAsset(entityType, ImageAssetType.BLACK_DARK, imageAssets);
            } else if ((!isWhiteField(firstClickedField)) &&
                    (isWhiteField(secondClickedField)) &&
                    (!isEntityWhite(entityDrawable))) {
                //Black Entity moves from a black field to a white field
                result = findImageAsset(entityType, ImageAssetType.BLACK_BRIGHT, imageAssets);
            }
        }
        return result;
    }

    public Drawable setFieldAfterMoveByButton(Button button) {
        if (isWhiteField(button)) {
            return defaultFieldWhite;
        } else {
            return defaultFieldBlack;
        }
    }

    private int getButtonIdOfField(Field field) {
        int fieldId = 0;
        for (Map.Entry<Integer, Field> fieldEntry : fieldMap.entrySet()) {
            if (fieldEntry.getValue().getFieldLocation() == field.getFieldLocation()) {
                fieldId = fieldEntry.getKey();
                break;
            }
        }
        return fieldId;
    }

    public void deserializeBoardToUI() {
        int buttonId = 0;

        tempFieldList = tempBoardForUI.getAllFieldsAsList();
        if ((Constants.getBoard() != null) && (tempBoardForUI != null)) {
            if (tempBoardForUI.getAllFieldsAsList() != tempBoard.getAllFieldsAsList()) {
                for (int i = 0; i < fieldList.size(); i++) {
                    if (!tempFieldList.get(i).equals(fieldList.get(i))) {
                        buttonId = getButtonIdOfField(tempFieldList.get(i));
                        Button btn = getButtonById(buttonId);
                        Drawable newDrawableForField = getDrawableByField(tempFieldList.get(i), btn);
                        btn.setBackground(newDrawableForField);
                    }
                }
            }
        }
    }

    public Drawable getDrawableByField(Field field, Button btn) {
        Drawable result = null;

        if (field != null) {
            result = getDrawableByEntityAndButton(field.getFieldEntity(), btn);
        }

        return result;
    }

    public Drawable getDrawableByEntityAndButton(IEntity<?> entity, Button button) {
        Drawable result = null;
        if (entity != null) {
            if (entity.getEntityColor().equals(EntityColor.WHITE)) {
                if (isWhiteField(button)) {
                    result = findImageAsset(entity, ImageAssetType.WHITE_BRIGHT, imageAssets);
                } else if (!isWhiteField(button)) {
                    result = findImageAsset(entity, ImageAssetType.WHITE_DARK, imageAssets);
                }
            } else if (entity.getEntityColor().equals(EntityColor.BLACK)) {
                if (isWhiteField(button)) {
                    result = findImageAsset(entity, ImageAssetType.BLACK_BRIGHT, imageAssets);
                } else if (!isWhiteField(button)) {
                    result = findImageAsset(entity, ImageAssetType.BLACK_DARK, imageAssets);
                }
            }
        } else {
            result = setFieldAfterMoveByButton(button);
        }
        return result;
    }

    private Drawable setFieldAfterMove() {
        if (isWhiteField(firstClickedField)) {
            return defaultFieldWhite;
        } else {
            return defaultFieldBlack;
        }
    }

    private Drawable findImageAsset(IEntity entityType, ImageAssetType assetType, Set<IEntity> entitySet) {
        Drawable result = null;
        Map<ImageAssetType, Drawable.ConstantState> entityDrawables;
        for (IEntity entity : entitySet) {
            if (entity.getEntityType().equals(entityType.getEntityType())) {
                entityDrawables = entity.getAllDrawables();
                for (Map.Entry<ImageAssetType, Drawable.ConstantState> entityEntry : entityDrawables.entrySet()) {
                    if (entityEntry.getKey().equals(assetType)) {
                        result = entityEntry.getValue().newDrawable();
                    }
                }
            }
        }
        return result;
    }

    private boolean isEntityWhite(Drawable entityDrawable) {
        if (imageCollectionContainsImageAsset(entityDrawable, whiteImageAssets, true)) {
            return true;
        } else {
            return false;
        }
    }

    private IEntity getEntityTypeFromDrawable(Drawable entityDrawable, Set<IEntity> imageCollection) {
        IEntity result = null;

        for (IEntity entityType : imageCollection) {
            Map<ImageAssetType, Drawable.ConstantState> entityDrawables = entityType.getAllDrawables();
            for (Map.Entry<ImageAssetType, Drawable.ConstantState> entityEntry : entityDrawables.entrySet()) {
                if (entityDrawable.getConstantState().equals(entityEntry.getValue())) {
                    result = entityType;
                }
            }
        }

        return result;
    }


    /**
     * @param imageAsset the Asset to be checked
     *                   checks if the imageAsset is existing in the imageAssets Collection
     * @return true if the imageAsset is existing in imageAssets
     */
    private boolean imageCollectionContainsImageAsset(Drawable imageAsset, Set<IEntity> imageCollection) {
        boolean result = false;
        for (IEntity entity : imageCollection) {
            Map<ImageAssetType, Drawable.ConstantState> entityDrawables = entity.getAllDrawables();
            if (entityDrawables.containsValue(imageAsset.getConstantState())) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean imageCollectionContainsImageAsset(Drawable imageAsset, Set<IEntity> imageCollection, boolean isEntityWhite) {
        boolean result = false;
        Map<ImageAssetType, Drawable.ConstantState> entityDrawables = null;
        for (IEntity entity : imageCollection) {
            if (isEntityWhite) {
                entityDrawables = entity.getWhiteDrawables();
            } else {
                entityDrawables = entity.getBlackDrawables();
            }
            if (entityDrawables.containsValue(imageAsset.getConstantState())) {
                result = true;
                break;
            }
        }
        return result;
    }

    private Boolean isWhiteField(Button field) {
        if (whiteFieldButtonList.contains(field)) {
            return true;
        } else if (blackFieldButtonList.contains(field)) {
            return false;
        } else {
            return null;
        }
    }

    /*
    set OnClickListener for all fields
     */
    private void setOnClickListenerForAllFields(List<Button> buttonList) {
        for (Button field : buttonList) {
            field.setOnClickListener(this);
        }
    }

    private boolean isFieldExisting(int ButtonID, List<Button> fieldList) {
        boolean result = false;
        for (Button button : fieldList) {
            if (button.getId() == ButtonID) {
                result = true;
                break;
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * @param ButtonID
     * @return the Button which has the same ID like @param
     */
    private Button getButtonById(int ButtonID) {
        Button result = null;
        for (Button button : this.fieldButtonList) {
            if (button.getId() == ButtonID) {
                result = button;
            }
        }
        return result;
    }

    private void setupFieldList() {
        //get all fields from 2D array (BOARD)
        if (Constants.getBoard() != null) {
            this.fieldList = Constants.getBoard().getAllFieldsAsList();
        }
    }

    private void setupFieldMap() {
        if (Constants.getBoard() != null) {
            for (int i = 0; i < fieldList.size(); i++) {
                if ((fieldList.get(i) != null) && (fieldButtonList.get(i) != null)) {
                    this.fieldMap.put(fieldButtonList.get(i).getId(), fieldList.get(i));
                }
            }
        }
    }

    private void setAllImageAssets() {
        //init
        Bishop bishop = new Bishop();
        King king = new King();
        Knight knight = new Knight();
        Pawn pawn = new Pawn();
        Queen queen = new Queen();
        Rook rook = new Rook();

        //fetching and setting all imageResources
        bishop.setAllDrawables(getResources());
        king.setAllDrawables(getResources());
        knight.setAllDrawables(getResources());
        pawn.setAllDrawables(getResources());
        queen.setAllDrawables(getResources());
        rook.setAllDrawables(getResources());

        bishop.setWhiteDrawables(getResources());
        king.setWhiteDrawables(getResources());
        knight.setWhiteDrawables(getResources());
        pawn.setWhiteDrawables(getResources());
        queen.setWhiteDrawables(getResources());
        rook.setWhiteDrawables(getResources());

        bishop.setBlackDrawables(getResources());
        king.setBlackDrawables(getResources());
        knight.setBlackDrawables(getResources());
        pawn.setBlackDrawables(getResources());
        queen.setBlackDrawables(getResources());
        rook.setBlackDrawables(getResources());

        //add to imageAssets Set
        this.imageAssets.add(bishop);
        this.imageAssets.add(king);
        this.imageAssets.add(knight);
        this.imageAssets.add(pawn);
        this.imageAssets.add(queen);
        this.imageAssets.add(rook);

        this.whiteImageAssets.add(bishop);
        this.whiteImageAssets.add(king);
        this.whiteImageAssets.add(knight);
        this.whiteImageAssets.add(pawn);
        this.whiteImageAssets.add(queen);
        this.whiteImageAssets.add(rook);

        this.blackImageAssets.add(bishop);
        this.blackImageAssets.add(king);
        this.blackImageAssets.add(knight);
        this.blackImageAssets.add(pawn);
        this.blackImageAssets.add(queen);
        this.blackImageAssets.add(rook);
    }
}