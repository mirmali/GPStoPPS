package gpsextraction_03;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
/*
 * extract_GPS_Data.java
 *
 * Created on April 21, 2008, 7:09 PM
 */

/**
 *
 * @author  mali1
 */
public class extract_GPS_Data extends javax.swing.JFrame {
    JFileChooser choose_file;
    File gps_wav_file;
    String file_path;
    String saved_text_file_path;
    String file_to_extract_data_from;
    
    JFileChooser new_normalized_gps_file;
    File new_gps_file;
    String new_gps_file_path_normalized;
    
    boolean same_as_previous;
    boolean use_normalized=false;
    float sampling_rate;
    String NMEA_file;
    double suggested_NMEA_transition;
    double suggested_PPS_height;
    double suggested_PPS_endHeight;
    double suggested_PPS_average;   
    double nmea_start;    
    double nmea_end;
    //double suggested_
    
    double sampleMax;
    double sampleMin;
    int signal_baud;
    
    
    
    
    
    
    
    /** Creates new form extract_GPS_Data */
    public extract_GPS_Data() {
        initComponents();
    }
    
    public void suggestValues(){        
        FileInputStream f;
        BufferedReader b;
        DataInputStream d;
        int sample_track=0;
        
        float prev_prev_prev_height=0;
        float prev_prev_height=0;
        float previous_height=0;
        float height=0; 
        
        String str;
        boolean sug_pps_found=false;
        boolean sug_pps_end=false;
        boolean adjust_height_if_needed=true;
        boolean PPS_done=false;
        boolean nmea=false;
        boolean adjust_nmea=false;
        
        int nmea_track_start=0;
        int nmea_track_end=0;
        int pps_start=0;
        int pps_end=0;
        int pps_width_in_samples = (int)(sampling_rate * Float.parseFloat(jTextField5.getText()));        
        try{
            f = new FileInputStream(saved_text_file_path);
            d = new DataInputStream(f);
            b = new BufferedReader(new InputStreamReader(d));
            for(int i=0;i<(int)sampling_rate*0.5;i++){
                b.readLine();
            }           
            while((str=b.readLine())!=null){
                sample_track = sample_track +  1;
               // System.out.println(str);
                if(sample_track==1){
                    prev_prev_prev_height = prev_prev_height = previous_height = height = Float.parseFloat(str);                    
                }
                if(sample_track==2){                    
                    prev_prev_height = previous_height = height = Float.parseFloat(str);                    
                }
                if(sample_track==3){
                    previous_height = height = Float.parseFloat(str);
                }
                if(sample_track==4){
                    height = Float.parseFloat(str);
                }
                if(sample_track>4){
                    prev_prev_prev_height = prev_prev_height;
                    prev_prev_height = previous_height;
                    previous_height = height;
                    height = Float.parseFloat(str);
                }
                
                if(adjust_nmea){
                    if(height>nmea_start)
                        nmea_start=height; 
                    System.out.println(nmea_start  +"   " + nmea_end);
                    break;
                    
                }
                
                
                if(PPS_done){
                     if(height-prev_prev_prev_height >= 0.5*sampleMax){
                         nmea = true;
                         nmea_start=height;
                         nmea_track_start=sample_track;                         
                         adjust_nmea=true;         
                  //       System.out.println(sample_track);
                         if(prev_prev_height < prev_prev_prev_height)
                             nmea_end = prev_prev_height;
                         else
                             nmea_end = prev_prev_prev_height;
                         continue;               
                     }
                     continue;
                }
                
                if(adjust_height_if_needed){
                    if(height > suggested_PPS_height)
                        suggested_PPS_height = height;
                    adjust_height_if_needed=false;
                    }
                
                
                if(sug_pps_found && (height - prev_prev_prev_height) <= -0.6*sampleMax){
                        // this could be the end of pps. Check for width                                            
                        pps_end=sample_track;
                        if((pps_end-pps_start)>= 0.7*pps_width_in_samples){                            
                            suggested_PPS_endHeight = prev_prev_prev_height;
                            PPS_done=true;
                            continue;                            
                        }else{
                            sug_pps_found=false;
                            continue;
                        }
                    }
                if(sug_pps_found && (height - prev_prev_prev_height)>=-0.6*sampleMax){
                    continue;
                }
                
                if((height - prev_prev_prev_height)>=0.6*sampleMax){                    
                    sug_pps_found = true;
                    suggested_PPS_height = height;
                    //suggested_PPS_height_0 = previous_height;                    
                    pps_start = sample_track;
                    adjust_height_if_needed=true;
                }
                
            }
            
           
        }catch(IOException e){
            
        }
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONVERT .WAV TO .TXT");

        jButton1.setText("Select WAV file");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Convert to TXT");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jLabel2.setText("Press to convert WAV to TXT");

        jLabel3.setText("Press to Normalize the TXT data");

        jButton3.setText("Normalize");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jLabel4.setText("Press to display WAV file information");

        jButton4.setText("WAV Info");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        buttonGroup3.add(jRadioButton5);
        jRadioButton5.setText("jRadioButton5");
        jRadioButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton5MouseClicked(evt);
            }
        });

        buttonGroup3.add(jRadioButton6);
        jRadioButton6.setText("jRadioButton6");
        jRadioButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(515, 515, 515)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(162, 162, 162)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButton5)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jRadioButton6)))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jButton4))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addComponent(jRadioButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton6)
                        .addContainerGap(50, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        jLabel5.setText("EXTRACT GPS INFORMATION FROM THE GPS SIGNAL");

        jLabel7.setText("Set the following parameters based on the signal observed. The \"height\" variables entered here must be the normalized values.");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Yes");
        jRadioButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });

        jLabel8.setText("Is the GPS TXT file same as the one converted above");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("No");
        jRadioButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });

        jLabel9.setText("If the file is not the same as converted above, select the file");

        jButton5.setText("Select TXT file");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jLabel10.setText("Baud Rate");

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText("4800");

        jLabel11.setText("Sampling Rate");

        jLabel12.setText("Enter sampling rate only if the file is not same as the converted file");

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("48000");

        jLabel13.setText("PPS signal Width (sec)");

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setText("0.02");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel14.setText("PPS Check %");

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setText("0.7");

        jLabel15.setText("PPS Height");

        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setText("0.7");

        jLabel16.setText("PPS Average Height");

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setText("0.4");

        jLabel17.setText("NMEA Transition ");

        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setText("0.5");

        jButton6.setText("Extract GPS");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jButton7.setText("Quit");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setText("Yes");
        jRadioButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton3MouseClicked(evt);
            }
        });

        jLabel6.setText("Use the Normalized file for Data Extraction");

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("No");
        jRadioButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton4MouseClicked(evt);
            }
        });

        jLabel18.setText("PPS Check % - the width of the PPS pulse to check to assure its a valid PPS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(19, 19, 19)
                .addComponent(jButton7)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField3, jTextField4, jTextField5, jTextField6, jTextField7, jTextField8, jTextField9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton6, jButton7});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jLabel6)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addGap(17, 17, 17)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jButton7)
                    .addComponent(jButton6))
                .addGap(22, 22, 22))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField3, jTextField4, jTextField5, jTextField6, jTextField7, jTextField8, jTextField9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton6, jButton7});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton6MouseClicked
// TODO add your handling code here:
        System.out.println("true 2");
    }//GEN-LAST:event_jRadioButton6MouseClicked

    private void jRadioButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton5MouseClicked
// TODO add your handling code here:
        System.out.println("true");
    }//GEN-LAST:event_jRadioButton5MouseClicked

    private void jRadioButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton4MouseClicked
// TODO add your handling code here:
        use_normalized=false;
    }//GEN-LAST:event_jRadioButton4MouseClicked

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jRadioButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton3MouseClicked
// TODO add your handling code here:
        
        if(same_as_previous)
            use_normalized = true;
        else
            use_normalized = false;
        System.out.println(use_normalized);
    }//GEN-LAST:event_jRadioButton3MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
// TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
// TODO add your handling code here:
        jTextArea1.append("\n  Extraction Started \n  please wait ...\n");
        extractGPS ext = new extractGPS();
        ext.NMEA_High_Low = Float.parseFloat(jTextField9.getText());
        ext.PPS_Average_height = Float.parseFloat(jTextField8.getText());
        ext.PPS_Height = Float.parseFloat(jTextField7.getText());
        ext.pps_check_percent = Float.parseFloat(jTextField6.getText());
        ext.PPS_Width = Float.parseFloat(jTextField5.getText());
        ext.baud = Integer.parseInt(jTextField3.getText());
        ext.Signal_Max = (float)sampleMax;                
        System.out.println("came here");
        if(!same_as_previous)
            ext.sampleRate = Integer.parseInt(jTextField4.getText());
        else{
            WavInfo info = new WavInfo(file_path);
            ext.sampleRate = info.get_sampleRate();
            
        }
      //  System.out.println(ext.sampleRate);
        String[] ss;
        System.out.println(use_normalized);
       // System.out.println(new_gps_file_path_normalized);
        if(use_normalized){
            System.out.println("using normalized");
            file_to_extract_data_from = new_gps_file_path_normalized;
             ss = file_to_extract_data_from.split(".txt");
            NMEA_file = ss[0] + "NMEA.txt";
            System.out.println(NMEA_file);
            //System.out.println(new_gps_file_path_normalized);        
            ext.extract(file_to_extract_data_from,NMEA_file,true);
        }
        else{
            System.out.println("not using");
            file_to_extract_data_from = saved_text_file_path;
            ss = file_to_extract_data_from.split(".txt");
            NMEA_file = ss[0] + "NMEA.txt";
            System.out.println(NMEA_file);
            //System.out.println(new_gps_file_path_normalized);
            System.out.println(file_to_extract_data_from);
            ext.extract(file_to_extract_data_from,NMEA_file,false);
            
        }
        System.out.println("Extraction done");
        reset();
        //System.exit(0);
        
        
    }//GEN-LAST:event_jButton6MouseClicked

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
// TODO add your handling code here:
        same_as_previous = false;
        //new_gps_file_path_normalized //= jTextField2.getText();
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
// TODO add your handling code here:
        same_as_previous = true;
        System.out.println(same_as_previous);
       // new_gps_file_path_normalized = 
        
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
// TODO add your handling code here:
        if(!same_as_previous){
            use_normalized = false;
            new_normalized_gps_file = new JFileChooser();
            new_normalized_gps_file.showOpenDialog(this);
        
            //Store the selected file in an Object of type File
            file_to_extract_data_from = new_normalized_gps_file.getSelectedFile().getPath();
            //gps_wav_file = new File(file_path);
           // System.out.println(file_to_extract_data_from);
            jTextField2.setText(file_to_extract_data_from);
        }
        
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
// TODO add your handling code here:
        // Normalize the data here
        FileInputStream fis;
        DataInputStream dis;
        BufferedReader buf;
        String str;
        int divideby=0;
        if(Math.abs(sampleMin) > Math.abs(sampleMax))
            divideby = (int)Math.abs(sampleMin);
        else
            divideby = (int)Math.abs(sampleMax);
            
        try{
       
            fis = new FileInputStream(saved_text_file_path);
            dis = new DataInputStream(fis);
            buf = new BufferedReader(new InputStreamReader(dis));
            new_gps_file_path_normalized = saved_text_file_path + "_normalized.txt";
            BufferedWriter normfile = new BufferedWriter(new FileWriter(new_gps_file_path_normalized));
            double w = 0;
            while((str=buf.readLine())!=null){
                 w = Double.parseDouble(str)/divideby;
                normfile.write(String.valueOf(w));
                normfile.newLine();
            }
            normfile.close();
        }catch(IOException e){
            
        }
        
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
// TODO add your handling code here:
        // convert to text here
        WavToTxt wavtotxt = new WavToTxt(file_path);
        saved_text_file_path = wavtotxt.convertToTxt((short)1024);
        sampleMax = wavtotxt.maxValue;
        sampleMin = wavtotxt.minValue;
        sampling_rate = wavtotxt.sampling_rate;
        suggestValues();
        // Dispaly the suggestedValues on the jTextArea
        jTextArea1.append("\n\n These are the suggestive Values for the \n" +
                " Height of the PPS signal at the start and end of the pulse. \n" +
                " These values can be used to decide the height parameters \n" +
                " while extracting the GPS data \n");
        jTextArea1.append("\n");
        jTextArea1.append(" Maximum height of the sample in the signal(Max_Sample_value) = " + sampleMax + "\n");
        jTextArea1.append("\n");
        jTextArea1.append(" Suggestive PPS Height start(PPS_Height_start) = " + suggested_PPS_height + "\n");
        jTextArea1.append(" Suggestive PPS Height end(PPS_Height_end) = " + suggested_PPS_endHeight + "\n");
        jTextArea1.append("\n");
       // jTextArea1.append((" Suggestive PPS_Height to use = ")+(int))
        jTextArea1.append((" Ratio of PPS_Height_start to Max_Sample_value(PPS_Height_start/Max_Sample_value) =" + (suggested_PPS_height/sampleMax)+"\n"));
        jTextArea1.append((" Ratio of PPS_Height_end to Max_Sample_value(PPS_Height_end/Max_Sample_value) =" + (suggested_PPS_endHeight/sampleMax)+"\n"));
        jTextArea1.append("\n");
        jTextArea1.append(" Suggestive NMEA Height start = " + nmea_start + "\n");
        jTextArea1.append(" Suggestive NMEA Height end = " + nmea_end + "\n");
        jTextArea1.append("\n");
        jTextArea1.append((" Ratio of NMEA_start to Max_Sample_value(NMEA_start/Max_Sample_value) =" + (nmea_start/sampleMax)+"\n"));
        jTextArea1.append((" Ratio of NMEA_start to Max_Sample_value(NMEA_end/Max_Sample_value) =" + (nmea_end/sampleMax)+"\n"));
        jTextArea1.append("\n");
        
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
// TODO add your handling code here:
        // start extracting wave file info.
     //   signal_baud = Integer.parseInt(jTextField10.getText());

        WavInfo wavinfo = new WavInfo(file_path);
        jTextArea1.setRows(10);
        jTextArea1.setText(" AUDIO_FORMAT :  " + wavinfo.get_Audioformat() + "\n" + 
        " NUMBER OF CHANNELS :  " + wavinfo.get_channels() + "\n" + 
        " RECORDING LENGTH IN FRAME :  " + wavinfo.get_length_in_frames() + "\n" + 
        " RECORDING SIZE IN BYTES :  " + wavinfo.get_recordingSizeinBytes() + "\n" + 
        " RECORDING TIME (seconds) :  " + wavinfo.get_length_in_frames()/wavinfo.get_frameRate() + "\n" + 
        " FRAME RATE (frames/second) :  " + wavinfo.get_frameRate() + "\n" + 
        " SAMPLING RATE (samples/second) :  " + (int)wavinfo.get_sampleRate() + "\n" + 
        " FRAME SIZE IN BYTES :  " + wavinfo.get_frameSize() + "\n" + 
        " DATA ARRANGED IN  " + wavinfo.get_dataFormat() +" ORDER"+ "\n" );       
        if(wavinfo.get_channels() !=1){
            jRadioButton5.setEnabled(false);
            jRadioButton6.setEnabled(false);
        }
            
    }//GEN-LAST:event_jButton4MouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
// TODO add your handling code here:
        
    }//GEN-LAST:event_formMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
// TODO add your handling code here:
        //Choose the file that you want to process
        choose_file = new JFileChooser();
        choose_file.showOpenDialog(this);
        
        //Store the selected file in an Object of type File
        file_path = choose_file.getSelectedFile().getPath();
        gps_wav_file = new File(file_path);
        jTextField1.setText(file_path);
    }//GEN-LAST:event_jButton1MouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new extract_GPS_Data().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
    
public void reset(){
    choose_file = null;
    gps_wav_file = null;
    file_path = null;
    saved_text_file_path = null;
    file_to_extract_data_from=null;
    
    new_normalized_gps_file=null;
    new_gps_file=null;
    new_gps_file_path_normalized=null;
    
    same_as_previous=false;
    use_normalized=false;
    sampling_rate=0;
    NMEA_file=null;
    suggested_NMEA_transition=0;
    suggested_PPS_height=0;
    suggested_PPS_endHeight=0;
    suggested_PPS_average=0;   
    nmea_start=0;    
    nmea_end=0;
    //double suggested_
    
    sampleMax=0;
    sampleMin=0;
    signal_baud=0;
    
}
    
}
