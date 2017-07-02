package localhost.spalanie;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

class FileHelper {

    public void initialDatabase() {
        if (isExistLocalCopy()) {
            importToExternalStorage();
        }
        // else seed data from initial insert
    }

    private boolean isExistLocalCopy() {
        File sd = Environment.getExternalStorageDirectory();
        String backupPath = DBName.DATA_BASE_NAME;
        File backupDB = new File(sd, backupPath);

        return backupDB.exists();
    }

    private void importToExternalStorage() {
        File source = Environment.getExternalStorageDirectory();
        File destination = Environment.getDataDirectory();

        String sourceDbPath = DBName.DATA_BASE_NAME;
        String destinationDbPath = "/data/" + "localhost.spalanie" + "/databases/" + DBName.DATA_BASE_NAME;

        File sourceDb = new File(source, sourceDbPath);
        File destinationDb = new File(destination, destinationDbPath);

        copyDbFile(sourceDb, destinationDb);
    }

    public void exportTOExternalStorage() {
        File source = Environment.getDataDirectory();
        File destination = Environment.getExternalStorageDirectory();

        String sourceDbPath = "/data/" + "localhost.spalanie" + "/databases/" + DBName.DATA_BASE_NAME;
        String destinationDbPath = DBName.DATA_BASE_NAME;

        File sourceDb = new File(source, sourceDbPath);
        File destinationDb = new File(destination, destinationDbPath);

        copyDbFile(sourceDb, destinationDb);
    }

    private void copyDbFile(File currentDB, File backupDB) {

        FileChannel sourceChannel;
        FileChannel destinationChannel;
        try {
            sourceChannel = new FileInputStream(currentDB).getChannel();
            destinationChannel = new FileOutputStream(backupDB).getChannel();
            destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            sourceChannel.close();
            destinationChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
