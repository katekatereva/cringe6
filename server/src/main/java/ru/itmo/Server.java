package ru.itmo;

import ru.itmo.dao.DAO;
import ru.itmo.dao.LabWorkFileStreamApiDAO;
import ru.itmo.dataManager.DataManager;
import ru.itmo.dataManager.FileDataManager;
import ru.itmo.dataManager.response.DataResponse;
import ru.itmo.dataManager.response.DataResponseType;
import ru.itmo.interactives.question.QuestionInteractive;
import ru.itmo.models.LabWork;
import ru.itmo.network.SelectorsOfServer.ServerConnect;

import java.util.*;

public class Server {
    private static final int BUFFER_SIZE = 60000;
    private static final int PORT = 6000;
    private static DAO<LabWork> dao;
    public static boolean isRunServer = true;
    private static Scanner scanner = new Scanner(System.in);



    private static void stopServer() {
        isRunServer = false;
    }


    public static FileDataManager initialFileDataManager() {

        String pathToFile = System.getenv("PATH_TO_COLLECTION");
        System.out.println(pathToFile);
        if (pathToFile == null) {
            System.out.println("Программа не смогла получить доступ к переменной окружения");
            return null;
        }
        return new FileDataManager(pathToFile);
    }
    private static boolean isRecreateFile(DataManager dataManager) {
        if (QuestionInteractive.yesOrNoQuestion("Хотите попробовать пересоздать файл?", scanner)) {
            if (dataManager.recreateFile()) {
                System.out.println("Файл успешно пересоздан");
                return true;
            } else {
                System.out.println("Не удалось пересоздать файл");
                return false;
            }
        }
        return false;
    }
    public static boolean importData(DataManager dataManager) {
        DataResponse dataResponse = dataManager.importData();
        if (dataResponse.getResponseType() == DataResponseType.PERMISSION_READ_DENIED) {
            System.out.println("Программа не смогла получить доступ к файлу для чтения");
            return false;
        } else if (dataResponse.getResponseType() == DataResponseType.BAD_FILE) {
            System.out.println("Данные были повреждены или хранятся в неправильном формате");
            isRecreateFile(dataManager);
        }
        dao = new LabWorkFileStreamApiDAO(dataResponse.getLabWorks());
        return true;
    }

    public static void main(String[] args) {

        DataManager<LabWork> dataManager = initialFileDataManager();

        if (dataManager == null) {
            stopServer();
        }

        if (isRunServer && !importData(dataManager)) {
            stopServer();
        }


        if (isRunServer) {
            ServerConnect serverConnect = new ServerConnect(PORT, BUFFER_SIZE, dataManager, dao);
            serverConnect.work();
        }


    }

}