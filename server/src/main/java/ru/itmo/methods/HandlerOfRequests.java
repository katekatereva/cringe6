package ru.itmo.methods;

import ru.itmo.dao.DAO;
import ru.itmo.dataManager.DataManager;
import ru.itmo.models.LabWork;
import ru.itmo.models.Person;
import ru.itmo.request.CommandRequest;
import ru.itmo.response.CommandResponse;
import ru.itmo.response.ResponseType;

import java.lang.reflect.Field;
import java.util.*;

public class HandlerOfRequests {

    public static CommandResponse handleRequestType(CommandRequest commandRequest, DataManager<LabWork> dataManager,
                                                     DAO<LabWork> dao) {
        CommandResponse commandResponse = new CommandResponse();
        switch (commandRequest.getRequestType()) {
            case CREATE -> {
                dao.create((LabWork) commandRequest.getObject());
                commandResponse.setObject(commandRequest.getObject());
                commandResponse.setResponseType(ResponseType.OK);
                dataManager.exportData(dao.getAll());

            }
            case GET -> {
                if (commandRequest.getParameters() == null) {
                    commandResponse.setObject(dao.getAll());
                    commandResponse.setTypeOfObject(ArrayDeque.class);
                    commandResponse.setResponseType(ResponseType.OK);
                } else {
                    Map<String, Object> parameters = commandRequest.getParameters();

                    ArrayDeque<LabWork> labWorks = dao.getAll();

                    ArrayDeque<LabWork> getLabWorks = new ArrayDeque<>();

                    for (LabWork labWork : labWorks) {

                        boolean isGet = true;

                        for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
                            Field field = null;
                            try {
                                field = labWork.getClass().getDeclaredField(parameter.getKey());
                            } catch (NoSuchFieldException e) {
                                commandResponse.setResponseType(ResponseType.MANAGER_ERROR);
                                return commandResponse;
                            }
                            field.setAccessible(true);


                            try {

                                if (Objects.equals(parameter.getKey(), "author")) {
                                    Person author = labWork.getAuthor();

                                    if (author == null) {
                                        isGet = false;
                                        break;
                                    }

                                    if (!Objects.equals(author.getName(), (String) parameter.getValue())) {
                                        isGet = false;
                                        break;
                                    }

                                } else if (Objects.equals(parameter.getKey(), "id")) {
                                    int id = (int) field.get(labWork);
                                    if (id != (int) parameter.getValue()) {
                                        isGet = false;
                                        break;
                                    }
                                }

                            } catch (IllegalAccessException e) {
                                commandResponse.setResponseType(ResponseType.MANAGER_ERROR);
                                return commandResponse;
                            }

                        }

                        if (isGet) {
                            getLabWorks.addLast(new LabWork(labWork));
                        }

                    }

                    commandResponse.setObject(getLabWorks);
                    commandResponse.setTypeOfObject(ArrayDeque.class);
                    commandResponse.setResponseType(ResponseType.OK);


                }
            }
            case GET_FIRST -> {
                try {
                    commandResponse.setObject(dao.getFirst());
                    commandResponse.setTypeOfObject(LabWork.class);
                    commandResponse.setResponseType(ResponseType.OK);
                } catch (NoSuchElementException noSuchElementException) {
                    commandResponse.setResponseType(ResponseType.NOT_FOUND);
                }
            }
            case DELETE -> {
                Map<String, Object> parameters = commandRequest.getParameters();

                boolean isHadDelete = false;

                if (commandRequest.getParameters() != null) {

                    ArrayDeque<LabWork> labWorks = dao.getAll();

                    for (LabWork labWork : labWorks) {

                        boolean isDelete = true;

                        for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
                            Field field = null;
                            try {
                                field = labWork.getClass().getDeclaredField(parameter.getKey());
                            } catch (NoSuchFieldException e) {
                                commandResponse.setResponseType(ResponseType.MANAGER_ERROR);
                                return commandResponse;
                            }
                            field.setAccessible(true);
                            try {
                                if ((int)field.get(labWork) != (int)parameter.getValue()) {
                                    isDelete = false;
                                    break;
                                }
                            } catch (IllegalAccessException e) {
                                commandResponse.setResponseType(ResponseType.MANAGER_ERROR);
                                return commandResponse;
                            }
                        }

                        if (isDelete) {
                            dao.delete(labWork);
                            isHadDelete = true;
                        }
                    }

                } else {
                    dao.clear();
                    isHadDelete = true;
                }


                if (isHadDelete) {
                    commandResponse.setResponseType(ResponseType.OK);
                    dataManager.exportData(dao.getAll());
                }

                else {
                    commandResponse.setResponseType(ResponseType.NOT_FOUND);
                }



            }

            case UPDATE -> {
                LabWork labWork = (LabWork) commandRequest.getObject();
                dao.update(labWork.getId(), labWork);
                commandResponse.setResponseType(ResponseType.OK);
                dataManager.exportData(dao.getAll());
            }

            case GET_MIN -> {
                LabWork labWork = dao.getMin();
                if (labWork == null) {
                    commandResponse.setResponseType(ResponseType.NOT_FOUND);

                } else {
                    commandResponse.setResponseType(ResponseType.OK);
                    commandResponse.setTypeOfObject(LabWork.class);
                    commandResponse.setObject(labWork);
                }

            }
            case INFO -> {
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("type", dao.getAll().getClass());
                parameters.put("date_initial", dataManager.getDateInitial());
                parameters.put("count", dao.getAll().size());
                commandResponse.setParameters(parameters);
                commandResponse.setResponseType(ResponseType.OK);

            }
            case DELETE_GREATER -> {
                LabWork lowLabwork = (LabWork) commandRequest.getObject();
                ArrayDeque<LabWork> labWorks = dao.getAll();

                for (LabWork labWork : labWorks) {

                    if (labWork.getName().length() > lowLabwork.getName().length()) {
                        dao.delete(labWork.getId());
                    }

                }
                commandResponse.setResponseType(ResponseType.OK);

                dataManager.exportData(dao.getAll());
            }
            case GET_MINIMAL_POINT -> {

                ArrayList<Integer> minimalPoints = new ArrayList<>();
                for (LabWork labWork : dao.getAll()) {
                    minimalPoints.add(labWork.getMinimalPoint());
                }

                minimalPoints.sort(Collections.reverseOrder());
                commandResponse.setObject(minimalPoints);
                commandResponse.setResponseType(ResponseType.OK);
            }
        }

        return commandResponse;

    }
}
