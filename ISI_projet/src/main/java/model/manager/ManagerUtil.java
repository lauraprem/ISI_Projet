package model.manager;

import model.robot.Robot;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class ManagerUtil {

    public static List<Robot> getUnoccupiedRobots(Manager manager) {
        return manager.getRobots().stream().filter(robot -> !robot.isBusy()).collect(Collectors.toList());
    }

    public static List<Robot> getBusyRobots(Manager manager) {
        return manager.getRobots().stream().filter(robot -> robot.isBusy()).collect(Collectors.toList());
    }
}
