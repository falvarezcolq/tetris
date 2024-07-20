/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package le;

import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.Fid.Fiv;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 *
 * @author admin@emapa.lan
 */
public class Capture extends javax.swing.JPanel implements ActionListener{

    /**
     * Creates new form Capture
     */
    
    private static final long serialVersionUID = 2;
	

    private JDialog       m_dlgParent;
    private CaptureThread m_capture;
    private Reader        m_reader;
//    private ImagePanel    m_image;
    private boolean       m_bStreaming;
    
    private BufferedImage m_image;
    
    public Capture(Reader reader, boolean bStreaming ) {
        initComponents();
        
        m_reader = reader;
        m_bStreaming = bStreaming;
        m_capture = new CaptureThread(m_reader, m_bStreaming, Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT);

    }
    
    private void StartCaptureThread(){
            m_capture = new CaptureThread(m_reader, m_bStreaming, Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT);
            m_capture.start(this);
    }

    private void StopCaptureThread(){
            if(null != m_capture) m_capture.cancel();
    }

    private void WaitForCaptureThread(){
            if(null != m_capture) m_capture.join(1000);
    }

    	public void actionPerformed(ActionEvent e){
//		if(e.getActionCommand().equals(ACT_BACK)){
//			//event from "back" button
//			//cancel capture
//			StopCaptureThread();
//		}
//		else 
                    
                    if(e.getActionCommand().equals(CaptureThread.ACT_CAPTURE)){
			//event from capture thread
			CaptureThread.CaptureEvent evt = (CaptureThread.CaptureEvent)e;
			boolean bCanceled = false;
			
			if(null != evt.capture_result){
				boolean bGoodImage = false;
				if(null != evt.capture_result.image){
					if(m_bStreaming && (Reader.CaptureQuality.GOOD == evt.capture_result.quality || Reader.CaptureQuality.NO_FINGER == evt.capture_result.quality)) bGoodImage = true;
					if(!m_bStreaming && Reader.CaptureQuality.GOOD == evt.capture_result.quality) bGoodImage = true;
				}
				if(bGoodImage){
					//display image
//					m_image.showImage(evt.capture_result.image);

                                        Fid.Fiv view = evt.capture_result.image.getViews()[0];
                                        m_image = new BufferedImage(view.getWidth(), view.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                                        m_image.getRaster().setDataElements(0, 0, view.getWidth(), view.getHeight(), view.getImageData());
                                            
//                                        Image image = evt.capture_result.image;
                                        Image image = m_image;
                                        
//                                        MessageBox.Warning("imagen de huella");
//                                        
                                        lblImage.setIcon(new ImageIcon(
                                                image.getScaledInstance(
                                                        lblImage.getWidth(), 
                                                        lblImage.getHeight(),  
                                                        Image.SCALE_DEFAULT
                                                )));
                                        repaint();
				}
				else if(Reader.CaptureQuality.CANCELED == evt.capture_result.quality){
					//capture or streaming was canceled, just quit
					bCanceled = true;
				}
				else{
					//bad quality
					MessageBox.BadQuality(evt.capture_result.quality);
				}
			}
			else if(null != evt.exception){
				//exception during capture
				MessageBox.DpError("Capture",  evt.exception);
				bCanceled = true;
			}
			else if(null != evt.reader_status){
				MessageBox.BadStatus(evt.reader_status);
				bCanceled = true;
			}
			
			if(!bCanceled){
				if(!m_bStreaming){
					//restart capture thread
					WaitForCaptureThread();
					StartCaptureThread();
				}
			}
			else{
				//destroy dialog
				m_dlgParent.setVisible(false);
			}
		}
	}

    
    private void doModal(JDialog dlgParent){
		//open reader
		try{
			m_reader.Open(Reader.Priority.COOPERATIVE);
		}
		catch(UareUException e){ MessageBox.DpError("Reader.Open()", e); }
		
		boolean bOk = true;
		if(m_bStreaming){
			//check if streaming supported
			Reader.Capabilities rc = m_reader.GetCapabilities();
			if(null != rc && !rc.can_stream){
				MessageBox.Warning("This reader does not support streaming");
				bOk = false;
			}
		}
		
		if(bOk){
			//start capture thread
			StartCaptureThread();
	
			//bring up modal dialog
			m_dlgParent = dlgParent;
			m_dlgParent.setContentPane(this);
			m_dlgParent.pack();
			m_dlgParent.setLocationRelativeTo(null);
			m_dlgParent.toFront();
			m_dlgParent.setVisible(true);
			m_dlgParent.dispose();
			
			//cancel capture
			StopCaptureThread();
			
			//wait for capture thread to finish
			WaitForCaptureThread();
		}
		
		//close reader
		try{
			m_reader.Close();
		}
		catch(UareUException e){ MessageBox.DpError("Reader.Close()", e); }
	}

    
    
    public static void Run(Reader reader, boolean bStreaming){
    	JDialog dlg = new JDialog((JDialog)null, "Poner huella en el lector", true);
    	Capture capture = new Capture(reader, bStreaming);
    	capture.doModal(dlg);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImage = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

        lblImage.setText("jLabel1");

        btnRegresar.setText("<< Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegresar)
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnRegresar)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        StopCaptureThread();
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel lblImage;
    // End of variables declaration//GEN-END:variables
}
