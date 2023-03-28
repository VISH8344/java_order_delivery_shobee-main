 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodms.ui.UI_Delivery;

import com.mycompany.oodms.Delivery;
import com.mycompany.oodms.DeliveryStaff;
import com.mycompany.oodms.OODMS_Main;
import static com.mycompany.oodms.OODMS_Main.frame;
import com.mycompany.oodms.Order;
import com.mycompany.oodms.OrderItem;
import com.mycompany.oodms.Services.DeliveryService;
import com.mycompany.oodms.Services.OrderItemService;
import com.mycompany.oodms.Services.OrderService;
import com.mycompany.oodms.ui.UI_Header;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author hw
 */
public class UI_Completed extends JPanel{
    UI_Header header;
    JLabel title;
    ImageIcon truck = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/truck.png");
    ImageIcon orangeCircle = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/orangeCircle.png");
    ImageIcon grayCircle1 = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/grayCircle.png");
    ImageIcon grayCircle2 = new ImageIcon("src/main/java/com/mycompany/oodms/ui/UI_Delivery/pictures/grayCircle.png");
    
    JLabel orderInfo;
    JScrollPane orderInfo_scrollPane;
    Dimension orderInfoSize;
    
    JButton upComingPage;
    JButton onGoingPage;
    JButton completedPage;
    
     public ArrayList<Delivery> initialize_delivery_data(){
        return DeliveryService.getDeliveryService().getDeliveryForStaffCompleted((DeliveryStaff) OODMS_Main.current_user);
    }
    
    public ArrayList<Order> initialize_order_data(ArrayList<Delivery>deliveries){
        return OrderService.getOrderService().getOrdersForDeliveryStaff(deliveries, (DeliveryStaff) OODMS_Main.current_user);
    }
    
    public ArrayList<OrderItem> initialize_order_item_data(ArrayList<Order> orders){
        return OrderItemService.getOrderItemService().getOrderItems(orders);
    }
   
    public UI_Completed() {
        ArrayList<Delivery> deliveries = initialize_delivery_data();
        ArrayList<Order> orders;
        ArrayList<OrderItem> orderItems;
        
        if(deliveries.isEmpty()){
            System.out.println("deliveries is empty in UI UPComing");
            orders = new ArrayList<Order>();
            orderItems = new ArrayList<OrderItem>();
        }else{
            orders = initialize_order_data(deliveries);
            orderItems = initialize_order_item_data(orders);
        }
        
        header = new UI_Header();
        header.setBounds(0,0,1080,50);
        
        // JLabel - title
        title = new JLabel(truck);
        title.setBounds(193,152,98,54);
        title.setFont(new Font("MV Boli",Font.BOLD,30));
        title.setPreferredSize(new Dimension(1080,150));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.BOTTOM);
        
        
        // JButton - upComingPage (direct to upcoming page)
        upComingPage = new JButton("up coming");
        upComingPage.setIcon(grayCircle1);
        upComingPage.setBorder(BorderFactory.createEmptyBorder());
        upComingPage.setHorizontalTextPosition(JLabel.CENTER);
        upComingPage.setVerticalTextPosition(JLabel.CENTER);
        upComingPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        upComingPage.setBounds(563,135,90,90);
        upComingPage.setFont(new Font("MV Boli",Font.PLAIN,12));
        upComingPage.setForeground(Color.GRAY);
        upComingPage.setOpaque(false);
        upComingPage.setFocusPainted(false);
        upComingPage.setContentAreaFilled(false);
        upComingPage.setOpaque(false);

        upComingPage.addActionListener(e -> {
                frame.replacePanel(new UI_UpComing());
        });
        
        // JButton - completed (direct to upcoming page)
        onGoingPage = new JButton("on going");
        onGoingPage.setIcon(grayCircle2);
        onGoingPage.setBorder(BorderFactory.createEmptyBorder());
        onGoingPage.setHorizontalTextPosition(JLabel.CENTER);
        onGoingPage.setVerticalTextPosition(JLabel.CENTER);
        onGoingPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        onGoingPage.setBounds(683,135,90,90);
        onGoingPage.setFont(new Font("MV Boli",Font.PLAIN,12));
        onGoingPage.setForeground(Color.GRAY);
        onGoingPage.setOpaque(false);
        onGoingPage.setFocusPainted(false);
        onGoingPage.setContentAreaFilled(false);
        onGoingPage.setOpaque(false);
        onGoingPage.addActionListener(e -> {
                frame.replacePanel(new UI_OnGoing());
        });
        
        // JButton - upComingPage (direct to upcoming page)
        completedPage = new JButton("completed");
        completedPage.setIcon(orangeCircle);
        completedPage.setBorder(BorderFactory.createEmptyBorder());
        completedPage.setHorizontalTextPosition(JLabel.CENTER);
        completedPage.setVerticalTextPosition(JLabel.CENTER);
        completedPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        completedPage.setBounds(803,135,90,90);
        completedPage.setFont(new Font("MV Boli",Font.PLAIN,12));
        completedPage.setForeground(Color.WHITE);
        completedPage.setOpaque(false);
        completedPage.setFocusPainted(false);
        completedPage.setContentAreaFilled(false);
        completedPage.setOpaque(false);

        
        
        
        // set JTable model
        DefaultTableModel model = new DefaultTableModel(){
            public Class<?> getColumnClass(int column)
            {
                // to set the data type of each column (mainly for the boolean - checkbox)
                switch(column)
                {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return String.class;
                }
            }
            // to make JTable uneditable accept the checkbox
            @Override 
            public boolean isCellEditable(int row, int column) 
            {
                // Make column other than checkbox read-only
                return false;
            }
        };
        
        
        // JTable - cart
        JTable deliveryTable = new JTable();
        deliveryTable.setModel(model);
        deliveryTable.getTableHeader().setReorderingAllowed(false);
        ListSelectionModel selectionModel = deliveryTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = deliveryTable.getSelectedRow();
                if (selectedRow != -1) {
                     // get data of selected row from table (base on table column index)
                    String deliveryId_display = String.valueOf(deliveryTable.getValueAt(selectedRow, 1));
                    String orderId_display = String.valueOf(deliveryTable.getValueAt(selectedRow, 2));
                    String status_display = String.valueOf(deliveryTable.getValueAt(selectedRow, 4));
                    String address = DeliveryService.getDeliveryService().getDelivery(Integer.parseInt(deliveryId_display)).getAddress().toString();
                    ArrayList<OrderItem> tempOrderItems = OrderItemService.getOrderItemService().getOrderItems(Integer.parseInt(orderId_display));
                    String tempOrderItemString = "<html>";
                     // set order item string
                    for(OrderItem item : tempOrderItems){
                            tempOrderItemString = tempOrderItemString + "<br>[ID: " +item.getProduct().getProductID()+ "]  " +  item.getProduct().getProductName() + " x " + item.getQuantity();
                    }
                    tempOrderItemString = tempOrderItemString + "</html>";
                   
                    // set text to the JLabel
                    orderInfo.setText("<html>Delivery ID : <br>" + deliveryId_display + 
                            "<br><br> Order ID : <br>" + orderId_display + 
                            "<br><br> Address : <br>" + address + 
                            "<br><br> Status : <br>" + status_display + 
                            "<br><br> Products : " + tempOrderItemString + 
                            "</html>");
                    orderInfoSize.height = 250 +  (18 * tempOrderItems.size());
                    orderInfo.setPreferredSize(orderInfoSize);
                }
            }
        });
        
       model.addColumn("Select");
        model.addColumn("DeliveryID");
        model.addColumn("OrderID");
        model.addColumn("City");
        model.addColumn("Status");

        // set cart table row
        
        for (int i = 0; i < deliveries.size(); i++) {
            Delivery delivery = deliveries.get(i);
            model.addRow(new Object[0]);
            model.setValueAt(i+1, i, 0);
            model.setValueAt(delivery.getDeliveryID(), i, 1);
            model.setValueAt(delivery.getOrder().getOrderID(), i, 2);
            model.setValueAt(delivery.getAddress().getCity(), i, 3);
            model.setValueAt(delivery.getStatus(), i, 4);
        }
        
        
        // set column size
        deliveryTable.setRowHeight(30);
         TableColumn selectColumn = deliveryTable.getColumnModel().getColumn(0);
        selectColumn.setPreferredWidth(5);
        selectColumn.setResizable(false);
        
        TableColumn deliveryIDColumn = deliveryTable.getColumnModel().getColumn(1);
        deliveryIDColumn.setPreferredWidth(10);
        deliveryIDColumn.setResizable(false);
        
        TableColumn orderIDColumn = deliveryTable.getColumnModel().getColumn(2);
        orderIDColumn.setPreferredWidth(10);
        orderIDColumn.setResizable(false);

        TableColumn statusColumn = deliveryTable.getColumnModel().getColumn(2);
        statusColumn.setPreferredWidth(10);
        statusColumn.setResizable(false);
        
        TableColumn addressColumn = deliveryTable.getColumnModel().getColumn(2);
        addressColumn.setPreferredWidth(10);
        addressColumn.setResizable(false);

        // scrollpane for JTable
        JScrollPane scrollPane = new JScrollPane(deliveryTable);
        scrollPane.setBounds(193,245,400,290);
        
        // JLabel - selected order information label
        orderInfo = new JLabel("Select a row to view the details");
        orderInfoSize = orderInfo.getPreferredSize();
        orderInfoSize.width = 300;
        orderInfo.setPreferredSize(orderInfoSize);
        orderInfo.setBackground(new Color(240,240,240));
        orderInfo.setOpaque(true);
        orderInfo.setHorizontalAlignment(JLabel.LEFT);
        orderInfo.setVerticalAlignment(JLabel.TOP);
        
        // JScrollPane - for orderInfo
        orderInfo_scrollPane = new JScrollPane(orderInfo);
        orderInfo_scrollPane.setBounds(593,245,300,290); 
        orderInfo.setOpaque(true);
        orderInfo_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        orderInfo_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // this
        this.setSize(1080, 768);
        this.setBackground(Color.white);
        this.setLayout(null);
        
        
        this.add(header);
        this.add(title);
        this.add(upComingPage);
        this.add(onGoingPage);
        this.add(completedPage);
        
        this.add(scrollPane);
        this.add(orderInfo_scrollPane);
        
    }
    
}
