package entity;

import java.io.Serializable;

import org.omg.PortableInterceptor.IORInterceptor;

public class StoreNode  implements Serializable{
		private String ip;
	    private int  port;
	    private double volum;
	    private double r_volum;
	    private double rem_volum;
	    private double File_num;
	    private String name;
	    private boolean isAiviable;
	    private int bord_port;
      public StoreNode(String ip,int port,double volum,double r_volum,double rem_volum,double File_num,String name,int bord_port,boolean isAiviable)
      {
    	  this.ip=ip;
    	  this.port=port;
    	  this.volum=volum;
    	  this.r_volum=r_volum; //Êµ¼Ê´æ´¢Á¿
    	  this.rem_volum=rem_volum;
    	  this.File_num=File_num;
    	  this.name=name;
    	  this.isAiviable=isAiviable;
    	  this.bord_port=bord_port;
        }
      public void setBord_port(int bord_port){
    	  this.bord_port=bord_port;
      }
      public int getBord_port(){
    	  return this.bord_port;
      }
        public StoreNode(){}
        public String getName(){
        	return name;
        }
        public void setFilenum(double num){
        	this.File_num=num;
        }
        public void setRem_volum(double rem_volum)
        {
        	this.rem_volum = rem_volum;
        }
        public void  setIsAiviable(boolean state)
        {
        	this.isAiviable=state;
        }
        public String getIp(){
        	return ip;
        }
        public int getPort(){
        	return port;
        }
        public double getVolum(){
        	return volum;
        }
        public double getR_volum(){
        	return r_volum;
        }
        public double getRem_volum(){
        	return rem_volum;
        }
        public double getFile_num(){
        	return File_num;
        }
        public boolean getIsaviable(){
        	return isAiviable;
        }
        
}
