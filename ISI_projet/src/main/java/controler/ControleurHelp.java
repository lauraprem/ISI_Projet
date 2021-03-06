package controler;

import model.manager.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.MainWindow;
import view.MenuLabel;
import view.about.AboutWindow;
import view.help.HelpWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControleurHelp implements ActionListener {
    private final static Logger logger = LogManager.getLogger();
    private Manager model;
    private MainWindow vue;

    public ControleurHelp(MainWindow vue, Manager model) {
        this.model = model;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case MenuLabel.HELP:
                HelpWindow helpWindow = new HelpWindow();
                helpWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent arg0) {
                        helpWindow.setVisible(false);
                    }
                });
                helpWindow.pack();
                helpWindow.setLocationRelativeTo(null);
                helpWindow.setVisible(true);
                logger.info(String.format("Action \"%s\" selected", MenuLabel.HELP_FR));
                break;

            case MenuLabel.ABOUT:
                AboutWindow windowsHelp = new AboutWindow();
                windowsHelp.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent arg0) {
                        windowsHelp.setVisible(false);
                    }
                });
                windowsHelp.pack();
                windowsHelp.setLocationRelativeTo(null);
                windowsHelp.setVisible(true);
                logger.info(String.format("Action \"%s\" selected", MenuLabel.ABOUT_FR));
                break;

            default:
                break;
        }
    }
}
