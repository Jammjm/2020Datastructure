package binaryTree;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import java.util.*;

//JPanel »ý¼º
public class TheWindow extends JPanel  {

   static JFrame frame;
   private BinaryTree tree = null;
   // the node location of the tree
   private HashMap nodeLocations = null;
   // the sizes of the subtrees
   private HashMap subtreeSizes = null;
   // do we need to calculate locations?
   private boolean repaint = true;
   // Default space between nodes
   private int parent2child = 15, child2child = 30;
   // helpers
   private Dimension empty = new Dimension(0, 0);
   private FontMetrics fm = null;
   private JTextField textField;
   
 
   public TheWindow(BinaryTree tree) {
      setBackground(Color.LIGHT_GRAY);
      this.tree = tree;
      nodeLocations = new HashMap();
      subtreeSizes = new HashMap();

      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      panel.setPreferredSize(new Dimension(300, 30));
      panel.setBackground(Color.WHITE);
      

      JPanel panel_1 = new JPanel();
      
      JLabel infolbl = new JLabel("");
      panel_1.add(infolbl);
      
      

      JLabel lblInput = new JLabel("Input:");
      panel.add(lblInput);

      textField = new JTextField();
      textField.setColumns(10);
      panel.add(textField);

      JButton InputBtn = new JButton("Input");
      panel.add(InputBtn);

      InputBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub

            try {
               int input = Integer.parseInt(textField.getText());
               if(input < 0 || input > 9 ) {
                   int a = input / 0;
               }
               tree.addNode(input);
               textField.setText("");
               repaint = true;
               repaint();
            } catch (NumberFormatException e) {
               infolbl.setText("This input is not integer format");
            }catch(ArithmeticException e) {
               infolbl.setText("Set integer between 0 and 9");
            }

         }
      });

      
      
      JButton SearchBtn = new JButton("Search");
      panel.add(SearchBtn);
      SearchBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub

            try {
                  int input = Integer.parseInt(textField.getText());
                  Node aux = tree.searchNode(input, tree.getRoot());
                  if (aux == null)
                     infolbl.setText("The number " + input + " was not found"); 
                  else
                     infolbl.setText("The number " + input + " was found"); 
                  repaint = true;
                  repaint();
               }
               
               catch (NumberFormatException z) {
                  infolbl.setText("This input is not integer format");
               }

         }
      });
      
      
      JButton DeleteBtn = new JButton("Delete");
      panel.add(DeleteBtn);
      DeleteBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub

            try {
               int input = Integer.parseInt(textField.getText());
               tree.deleteNode(input, tree.getRoot());
               repaint = true;
               repaint();
            } catch (NumberFormatException z) {
               infolbl.setText("This input is not integer format");
            }

         }
      });
      setLayout(new BorderLayout(0, 0));
      
      
      
      
      
      
      
      add(panel_1,BorderLayout.SOUTH);
      
      add(panel,BorderLayout.NORTH);
   }

   

   // This method calculates the node locations, to make it look stablish
   private void calculateLocations() {
      nodeLocations.clear();
      subtreeSizes.clear();
      Node root = tree.getRoot();
      if (root != null) {
         calculateSubtreeSize(root);
         calculateLocation(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 50);
      }
   }

   // This method calculates the size of a subtree rooted at n
   private Dimension calculateSubtreeSize(Node n) {
      if (n == null)
         return new Dimension(0, 0);
      String s = Integer.toString(n.getContent());
      Dimension ld = calculateSubtreeSize(n.getLeft());
      Dimension rd = calculateSubtreeSize(n.getRight());
      int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
      int w = ld.width + child2child + rd.width;
      Dimension d = new Dimension(w, h);
      subtreeSizes.put(n, d);
      return d;
   }

   // This method calculates the location of the nodes in the subtree rooted at n
   private void calculateLocation(Node n, int left, int right, int top) {
      if (n == null)
         return;
      Dimension ld = (Dimension) subtreeSizes.get(n.getLeft());
      if (ld == null)
         ld = empty;
      Dimension rd = (Dimension) subtreeSizes.get(n.getRight());
      if (rd == null)
         rd = empty;
      int center = 0;
      if (right != Integer.MAX_VALUE)
         center = right - rd.width - child2child / 2;
      else if (left != Integer.MAX_VALUE)
         center = left + ld.width + child2child / 2;
      int width = fm.stringWidth(Integer.toString(n.getContent()));
      Ellipse2D r = new Ellipse2D.Double(center - width / 2 - 3, top, width + 20, fm.getHeight());
      
      nodeLocations.put(n, r);
      calculateLocation(n.getLeft(), Integer.MAX_VALUE, center - child2child / 2,
            top + fm.getHeight() + parent2child);
      calculateLocation(n.getRight(), center + child2child / 2, Integer.MAX_VALUE,
            top + fm.getHeight() + parent2child);
   }

   // This method draws the tree using the pre-calculated locations. We need
   // necessary a graphic
   private void drawTree(Graphics2D g, Node n, int px, int py, int yoffs) {
      if (n == null)
         return;
      Ellipse2D r = (Ellipse2D) nodeLocations.get(n);
      g.draw(r);
      g.drawString(Integer.toString(n.getContent()), (int)(r.getX()) + 10, (int)(r.getY()) + yoffs);
      if (px != Integer.MAX_VALUE)
         g.drawLine(px, py, (int)(r.getX()) + (int)(r.getWidth()) / 2, (int)(r.getY()));
      drawTree(g, n.getLeft(), (int)(r.getX()) + (int)(r.getWidth()) / 2, (int)(r.getY()) + (int)(r.getHeight()), yoffs);
      drawTree(g, n.getRight(), (int)(r.getX()) + (int)(r.getWidth()) / 2, (int)(r.getY()) + (int)(r.getHeight()), yoffs);
   }

   // This method will draw our tree, this receives a graphic called "g"
   public void paint(Graphics g) {
      super.paint(g);
      fm = g.getFontMetrics();
      // if node locations not calculated
      if (repaint) {
         calculateLocations();
         repaint = false;
      }
      Graphics2D g2d = (Graphics2D) g;
      g2d.translate(getWidth() / 2, parent2child);
      drawTree(g2d, tree.getRoot(), Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
      fm = null;
   }

   /*
    * At the start of the program will show a messagebox with all the commands that
    * can be used to work this program correctly,also set the dimension of the
    * principal window
    */
   public static void main(String[] args) {
      // TODO code application logic here
      BinaryTree tree = new BinaryTree();
      JFrame f = new JFrame("Team4 BST");
      f.getContentPane().add(new TheWindow(tree));
      
      f.setBounds(100, 100, 700, 700);
      f.show();
   }
}