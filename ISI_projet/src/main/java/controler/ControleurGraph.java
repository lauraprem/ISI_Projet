package controler;

import model.manager.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.MainWindow;
import view.MenuLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurGraph implements ActionListener {
    private final static Logger logger = LogManager.getLogger();
    private Manager model;
    private MainWindow vue;
    private ControleurMap controleurMap;

    public ControleurGraph(MainWindow vue, Manager model, ControleurMap controleurMap) {
        this.model = model;
        this.vue = vue;
        this.controleurMap = controleurMap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int typeElement;
        typeElement = -1;

        switch (e.getActionCommand()) {
            case MenuLabel.ADD_NODE:
                typeElement = 4;
                logger.info(String.format("Action \"%s\" selected", MenuLabel.ADD_NODE_FR));
                break;

            case MenuLabel.ADD_FLOODED_EDGE:
                typeElement = 5;
                logger.info(String.format("Action \"%s\" selected", MenuLabel.ADD_FLOODED_EDGE_FR));
                break;

            case MenuLabel.ADD_STEEP_EDGE:
                typeElement = 6;
                logger.info(String.format("Action \"%s\" selected", MenuLabel.ADD_STEEP_EDGE_FR));
                break;

            case MenuLabel.ADD_FLAT_EDGE:
                typeElement = 7;
                logger.info(String.format("Action \"%s\" selected", MenuLabel.ADD_FLAT_EDGE_FR));
                break;

            default:
                break;
        }
        controleurMap.setTypeElement(typeElement);
    }
}
