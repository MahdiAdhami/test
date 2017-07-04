package CrossWalk.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import CrossWalk.Const;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LineMenu extends Menu {

    public LineMenu() {
        super("نوع خط کشی را انتخاب کنید", 400, 400);
    }

    @Override
    protected void createPanel() {
        JPanel panel = new JPanel(new BorderLayout(1, 1));
        panel.setBorder(new EmptyBorder(50, 10, 50, 10));

        frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(4, 0));
        JPanel labels = new JPanel(new GridLayout(6, 0));
        JPanel submit = new JPanel(new GridLayout(1, 0));

        panel.add(controls, BorderLayout.WEST);
        panel.add(labels, BorderLayout.EAST);
        panel.add(submit, BorderLayout.SOUTH);

        String[] lineImages = new File(Const.ROOT_PATH + Const.LINE_IMAGE_ROOT_PATH).list();

        Object[] items = new Object[lineImages.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = new ImageIcon(Const.ROOT_PATH + Const.LINE_IMAGE_ROOT_PATH + "\\" + lineImages[i]);
        }

        JComboBox combo = new JComboBox(items);

        JLabel comboLabel = createLabel("نوع خط کشی را انتخاب کنید:");
        labels.add(comboLabel);

        controls.add(combo);

        JButton saveChanges = createButton("ذخیره",
                (ActionEvent e) -> {
                    GameSetting.setLineImageNumber(combo.getSelectedIndex() + 1);
                    frame.dispose();
                });

        submit.add(saveChanges);
    }

}
