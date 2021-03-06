
package CrossWalk.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import CrossWalk.Utilities.Const;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SheepMenu extends Menu{

    public SheepMenu() {
        super("انتخاب نوع آدمک", 400, 400);
    }

    @Override
    protected void createPanel() {
        //initliaze the frame
        JPanel panel = new JPanel(new BorderLayout(1, 1));
        panel.setBorder(new EmptyBorder(5, 10, 20, 10));

        Frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(6, 0));

        JPanel labels = new JPanel(new GridLayout(6, 0));

        JPanel submit = new JPanel(new GridLayout(1, 0));

        panel.add(controls, BorderLayout.CENTER);
        panel.add(labels, BorderLayout.EAST);
        panel.add(submit, BorderLayout.SOUTH);
        
        //getting images of each sheep
        String[] sheepImages = new File(Const.ROOT_PATH + Const.SHEEP_ROOT_IMAGE).list();
        Object[] items = new Object[sheepImages.length/4];
        
        //adding a combobox with sheep images to frame
        for(int i = 0  , j = 1 ; j <= items.length; i+=4 , j++)
        {
            items[j - 1] = new ImageIcon(Const.ROOT_PATH + Const.SHEEP_ROOT_IMAGE + "\\" + sheepImages[i]);
        }
        
        JComboBox combo = new JComboBox(items);
        
        JLabel comboLabel = createLabel("نوع آدمک را انتخاب کنید:");
        labels.add(comboLabel);
        labels.add(createMargin(10, 0, 10, 0));
        
        controls.add(combo);
        
        //save selected sheep index to setting files
        JButton saveChanges = createButton("ذخیره",
                (ActionEvent e) -> {
                        GameSetting.setSheepImageNumber(combo.getSelectedIndex() + 1);
                        Frame.dispose();
                });
        
        submit.add(saveChanges);
        
    }
    
}
