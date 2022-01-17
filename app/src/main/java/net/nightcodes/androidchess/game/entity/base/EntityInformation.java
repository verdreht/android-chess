package net.nightcodes.androidchess.game.entity.base;

import android.os.Build;

import androidx.annotation.RequiresApi;

import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class EntityInformation {

    private final EntityIdentification identification;

    private EntityInformation(EntityIdentification identification) {
        this.identification = identification;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static EntityInformation getInstance(IEntity<?> entity) {
        EntityIdentification identification = entity.getEntityType().getAnnotation(EntityIdentification.class);
        return new EntityInformation(identification);
    }

    public String getName() {
        return identification.name();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Path getImageAsset() {
        return new File(identification.imageAsset()).toPath();
    }

    public List<MovementPermission> getPermissions() {
        return Arrays.asList(identification.movementPermission());
    }

}
