package saks.interviewproject.service;

public class WsResponse <E> {

		private boolean success;
		private String msg;
		private E data;

		public boolean isSuccess() {
			return success;
		}
		
		public void setSuccess(boolean success) {
			this.success = success;
		}
		
		public String getMessage() {
			return msg;
		}
		
		public void setMessage(String message) {
			this.msg = message;
		}
		
		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}
		
		// Use this constructor for a success response.
	    public WsResponse() {

	    }
		
		// Use this constructor for a success response.
	    public WsResponse(boolean success, String msg, E data) {
	        this.success = success;
	        this.msg = msg;
	        this.data = data;
	        //this.pdata = data;
	    }
	    
	    
	    // Use this constructor for a failure response, or for a success
	    // response which does not return data.
	    public WsResponse(boolean success, String msg) {
	        this.success = success;
	        this.msg = msg;
	    }
		
}
