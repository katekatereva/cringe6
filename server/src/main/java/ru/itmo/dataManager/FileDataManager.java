package ru.itmo.dataManager;



import ru.itmo.dataManager.models.LabWorkCollection;
import ru.itmo.dataManager.response.DataResponse;
import ru.itmo.dataManager.response.DataResponseType;
import ru.itmo.models.LabWork;
import ru.itmo.models.utils.LabWorkValidator;
import ru.itmo.utils.GeneratorID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class FileDataManager extends DataManager<LabWork> {
    private final String pathToFile;
    private LocalDate dateInitial;

    public FileDataManager(String path) {
        this.pathToFile = path;
    }

    private Unmarshaller createUnmarshaller() {
        JAXBContext jaxbContext;
        Unmarshaller unmarshaller = null;
        try {
            jaxbContext = JAXBContext.newInstance(LabWorkCollection.class);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException ignored) {
        }
        return unmarshaller;
    }

    private Marshaller createMarshaller() {
        JAXBContext jaxbContext;
        Marshaller marshaller = null;
        try {
            jaxbContext = JAXBContext.newInstance(LabWorkCollection.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ignored) {
        }
        return marshaller;
    }

    private LabWorkCollection unmarshalLabWorkCollection(Unmarshaller unmarshaller, String xml) {
        LabWorkCollection labWorkCollection = null;

        try {
            labWorkCollection = (LabWorkCollection) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException ignored) {
        }

        return labWorkCollection;
    }

    private Integer checkOnValidIDForLabWork(LabWorkCollection labWorkCollection) {

        Integer maxId = 0;

        ArrayList<Integer> idOfLabWorks = new ArrayList<>();

        for (LabWork labWork : labWorkCollection.getLabWorks()) {

            if (labWork.getId() <= 0) {
                return null;
            }

            if (idOfLabWorks.contains(labWork.getId())) {
                return null;
            }

            idOfLabWorks.add(labWork.getId());

            if (maxId < labWork.getId()) {
                maxId = labWork.getId();
            }

        }
        return maxId;
    }

    private boolean checkOnValidDataFromXML(LabWorkCollection labWorkCollection) {
        ArrayDeque<LabWork> labWorks = labWorkCollection.getLabWorks();
        if (labWorks != null) {

            for (LabWork labWork : labWorks) {

                if (!LabWorkValidator.isValidLabWork(labWork)) {
                    return false;
                }
            }
        }

        return true;
    }

    private void setNewValueForGeneratorId(int id) {
        GeneratorID.setId(id + 1);
    }

    private DataResponse readDataFromXml(Path fileData) {

        DataResponse dataResponse = new DataResponse();

        try (Scanner scanner = new Scanner(fileData)) {

            Unmarshaller unmarshaller = createUnmarshaller();


            if (unmarshaller == null) {
                dataResponse.setResponseType(DataResponseType.BAD_FILE);
                return dataResponse;
            }


            StringBuilder xmlFromDataFile = new StringBuilder();
            while (scanner.hasNext()) {
                xmlFromDataFile.append(scanner.nextLine());
            }

            LabWorkCollection labWorkCollection = unmarshalLabWorkCollection(unmarshaller,
                    xmlFromDataFile.toString());



            if (labWorkCollection == null) {
                dataResponse.setResponseType(DataResponseType.BAD_FILE);
                return dataResponse;
            }

            if (labWorkCollection.getDateInitial() == null) {
                dataResponse.setResponseType(DataResponseType.BAD_FILE);
                return dataResponse;
            }

            this.dateInitial = labWorkCollection.getDateInitial();


            if (labWorkCollection.getLabWorks() != null) {
                if (!checkOnValidDataFromXML(labWorkCollection)) {
                    dataResponse.setResponseType(DataResponseType.BAD_FILE);
                    return dataResponse;
                }


                Integer maxId = checkOnValidIDForLabWork(labWorkCollection);

                if (maxId == null) {
                    dataResponse.setResponseType(DataResponseType.BAD_FILE);
                    return dataResponse;
                }

                setNewValueForGeneratorId(maxId);
            }


            dataResponse.setLabWorks(labWorkCollection.getLabWorks());
            dataResponse.setResponseType(DataResponseType.OK);

        } catch (IOException e) {
            dataResponse.setResponseType(DataResponseType.BAD_FILE);
            return dataResponse;
        }
        return dataResponse;
    }

    private DataResponse writeDataToXml(Path fileData, Collection<LabWork> data) {

        DataResponse dataResponse = new DataResponse();

        try (
                FileOutputStream out = new FileOutputStream(fileData.toFile());
                BufferedOutputStream bos = new BufferedOutputStream(out)
        ) {

            Marshaller marshaller = createMarshaller();

            LabWorkCollection labWorkCollection = new LabWorkCollection();
            labWorkCollection.setDateInitial(dateInitial);


            ArrayDeque<LabWork> labWorks = new ArrayDeque<>(data);
            labWorkCollection.setLabWorks(labWorks);


            marshaller.marshal(labWorkCollection, bos);
            dataResponse.setResponseType(DataResponseType.OK);
        } catch (IOException | JAXBException ex) {
            dataResponse.setResponseType(DataResponseType.BAD_DATA);
        }
        return dataResponse;
    }

    @Override
    public DataResponse importData() {

        DataResponse dataResponse = new DataResponse();

        Path fileData = Path.of(pathToFile);

        if (Files.isReadable(fileData)) {
            dataResponse = readDataFromXml(fileData);
        } else {
            dataResponse.setResponseType(DataResponseType.PERMISSION_READ_DENIED);
            return dataResponse;
        }
        return dataResponse;
    }

    @Override
    public DataResponse exportData(Collection<LabWork> data) {

        DataResponse dataResponse = new DataResponse();

        Path fileData = Path.of(pathToFile);


        if (Files.isWritable(fileData)) {
            writeDataToXml(fileData, data);
        } else {
            dataResponse.setResponseType(DataResponseType.PERMISSION_WRITE_DENIED);
        }
        return dataResponse;
    }

    @Override
    public boolean recreateFile() {

        File file = new File(pathToFile);
        try (
                FileOutputStream out = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(out))
        {
            Marshaller marshaller = createMarshaller();
            LabWorkCollection labWorkCollection = new LabWorkCollection();
            this.dateInitial = LocalDate.now();
            labWorkCollection.setDateInitial(dateInitial);
            marshaller.marshal(labWorkCollection, bos);
            return true;
        } catch (IOException | JAXBException e) {
            return false;
        }
    }

    @Override
    public LocalDate getDateInitial() {
        return dateInitial;
    }






}
