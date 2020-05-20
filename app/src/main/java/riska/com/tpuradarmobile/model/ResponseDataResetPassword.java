package riska.com.tpuradarmobile.model;

import com.google.gson.annotations.SerializedName;

public class ResponseDataResetPassword{

	@SerializedName("dataResetPassword")
	private DataResetPassword dataResetPassword;

	@SerializedName("status")
	private int status;

	public DataResetPassword getDataResetPassword(){
		return dataResetPassword;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDataResetPassword{" + 
			"dataResetPassword = '" + dataResetPassword + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}