package com.enums;

public enum UrlImg {
	AMGAPI_NoComplete("amigoemapuros1_nocomplete.jpg"), AMGAPII_NoComplete(
			"amigoemapuros2_nocomplete.jpg"), AMGAPIII_NoComplete("amigoemapuros3_nocomplete.jpg"), AMGAPIV_NoComplete(
					"amigoemapuros4_no"
					+ "complete.jpg"), CONQI_NoComplete("conquistador1_nocomplete.jpg"), CONQII_NoComplete(
							"conquistador2_nocomplete.jpg"), CONQIII_NoComplete(
									"conquistador3_nocomplete.jpg"), CONQIV_NoComplete(
											"conquistador4_nocomplete.jpg"), TRAEQ_NoComplete(
													"trabalhandocomaequipe_nocomplete.jpg"), HERSPRINT_NoComplete(
															"heroidasprint_nocomplete.jpg"), AMGAPI_Complete(
																	"amigoemapuros1_complete.jpg"), AMGAPII_Complete(
																			"amigoemapuros2_complete.jpg"), AMGAPIII_Complete(
																					"amigoemapuros3_complete.jpg"), AMGAPIV_Complete(
																							"amigoemapuros4_complete.jpg"), CONQI_Complete(
																									"conquistador1_complete.jpg"), CONQII_Complete(
																											"conquistador2_complete.jpg"), CONQIII_Complete(
																													"conquistador3_complete.jpg"), CONQIV_Complete(
																															"conquistador4_complete.jpg"), TRAEQ_Complete(
																																	"trabalhandocomaequipe_complete.jpg"), HERSPRINT_Complete(
																																			"heroidasprint_complete.jpg");

	UrlImg(String url) {
		this.url = url;
	}

	private String url;

	public String getUrl() {
		return this.url;
	}
}
