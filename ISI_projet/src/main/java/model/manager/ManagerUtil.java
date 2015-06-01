package model.manager;

import model.robot.Robot;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class ManagerUtil {
    /**
     * Permet de connaitre les robots non occupés pour un manager
     *
     * @param manager dont on veut connaitre les robots innocupés
     * @return la liste des robot innocupés
     */
    public static List<Robot> getUnoccupiedRobots(Manager manager) {
        return manager.getRobots().stream().filter(robot -> !robot.isBusy()).collect(Collectors.toList());
    }
}
