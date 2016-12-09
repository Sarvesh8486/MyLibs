create or replace PACKAGE xxorc_create_pa_request_pkg
AS
  /* $Header: $ */
  /*#
  * This interface creates project request.
  * @rep:scope public
  * @rep:product PA
  * @rep:displayname xxorc_create_pa_request_pkg
  * @rep:lifecycle active
  * @rep:compatibility S
  * @rep:category BUSINESS_ENTITY PA_PROJECT
  */
  /*#
  * Returns Project Request NUMBER
  * @param p_request_name VARCHAR2 Request Name
  * @param p_request_type VARCHAR2 Request Type
  * @param p_request_status_code VARCHAR2 request_status_code
    * @param p_request_status_name VARCHAR2 request_status_nam
  * @param p_description VARCHAR2 description
  * @param p_expected_proj_approval_date VARCHAR2 proj_approval_date
    * @param p_source_type VARCHAR2 source_type
  * @param p_application_id NUMBER application_id
  * @param p_source_id NUMBER source_id 
    * @param p_source_object VARCHAR2 source_object
  * @param p_source_reference VARCHAR2 source_reference
  * @param p_value VARCHAR2 value
    * @param p_currency_code VARCHAR2 currency_code
  * @param p_cust_party_id NUMBER cust_party_id
  * @param p_cust_party_name VARCHAR2 cust_party_name
    * @param p_cust_party_site_id NUMBER cust_party_site_id
  * @param p_cust_party_site_name VARCHAR2 cust_party_site_name
  * @param p_cust_account_id NUMBER cust_account_id
    * @param p_cust_account_name VARCHAR2 cust_account_name
  * @param p_attribute_category VARCHAR2 attribute_category
  * @param p_attribute1 VARCHAR2 attribute1
    * @param p_attribute2 VARCHAR2 attribute2
  * @param p_attribute3 VARCHAR2 attribute3
  * @param p_attribute4 VARCHAR2 attribute4
    * @param p_attribute5 VARCHAR2 attribute5
  * @param p_attribute6 VARCHAR2 attribute6
  * @param p_attribute7 VARCHAR2 attribute7
    * @param p_attribute8 VARCHAR2 attribute8
    * @param p_attribute9 VARCHAR2 attribute9
  * @param p_attribute10 VARCHAR2 attribute10
  * @param p_attribute11 VARCHAR2 attribute11
    * @param p_attribute12 VARCHAR2 attribute12
  * @param p_attribute13 VARCHAR2 attribute13
  * @param p_attribute14 VARCHAR2 attribute14
    * @param p_attribute15 VARCHAR2 attribute15
  * @param p_create_rel_flag VARCHAR2 create_rel_flag
  * @param p_api_version VARCHAR2 api_version
    * @param p_init_msg_list VARCHAR2 init_msg_list
  * @param p_commit VARCHAR2 commit
  * @param p_validate_only VARCHAR2 validate_only
   * @param p_max_msg_count VARCHAR2 max_msg_count
  * @return x_request_number
  * @rep:scope public
  * @rep:lifecycle active
  * @rep:displayname Project Request Number
  */
  FUNCTION create_project_request(
		p_request_name                  IN pa_project_requests.request_name%TYPE,
        p_request_type                  IN pa_project_requests.request_type%TYPE default null,
        p_request_status_code           IN pa_project_requests.status_code%TYPE default null,
        p_request_status_name           IN pa_project_statuses.project_status_name%TYPE default null,
        p_description                   IN pa_project_requests.description%TYPE default null,
        p_expected_proj_approval_date   IN pa_project_requests.expected_project_approval_date%TYPE default null,
        p_closed_date                   IN pa_project_requests.closed_date%TYPE default null,
        p_source_type                   IN pa_project_requests.source_type%TYPE :='ORACLE_APPLICATION',
        p_application_id                IN pa_project_requests.application_id%TYPE default null,
        p_source_id                     IN NUMBER default null,
        p_source_object                 IN pa_object_relationships.object_type_from%TYPE default null,
        p_source_reference              IN pa_project_requests.source_reference%TYPE default null,
        p_value                         IN pa_project_requests.value%TYPE default null,
        p_currency_code                 IN pa_project_requests.currency_code%TYPE default null,
        p_cust_party_id                 IN pa_project_requests.cust_party_id%TYPE default null,
        p_cust_party_name               IN hz_parties.party_name%TYPE default null,
        p_cust_party_site_id            IN pa_project_requests.cust_party_site_id%TYPE default null,
        p_cust_party_site_name          IN hz_party_sites.party_site_name%TYPE default null,
        p_cust_account_id               IN pa_project_requests.cust_account_id%TYPE default null,
        p_cust_account_name             IN hz_cust_accounts.account_name%TYPE default null,
        p_source_org_id                 IN pa_project_requests.source_org_id%TYPE default null,
        p_attribute_category            IN pa_project_requests.attribute_category%TYPE default null,
        p_attribute1                    IN pa_project_requests.attribute1%TYPE default null,
        p_attribute2                    IN pa_project_requests.attribute2%TYPE default null,
        p_attribute3                    IN pa_project_requests.attribute3%TYPE default null,
        p_attribute4                    IN pa_project_requests.attribute4%TYPE default null,
        p_attribute5                    IN pa_project_requests.attribute5%TYPE default null,
        p_attribute6                    IN pa_project_requests.attribute6%TYPE default null,
        p_attribute7                    IN pa_project_requests.attribute7%TYPE default null,
        p_attribute8                    IN pa_project_requests.attribute8%TYPE default null,
        p_attribute9                    IN pa_project_requests.attribute9%TYPE default null,
        p_attribute10                   IN pa_project_requests.attribute10%TYPE default null,
        p_attribute11                   IN pa_project_requests.attribute11%TYPE default null,
        p_attribute12                   IN pa_project_requests.attribute12%TYPE default null,
        p_attribute13                   IN pa_project_requests.attribute13%TYPE default null,
        p_attribute14                   IN pa_project_requests.attribute14%TYPE default null,
        p_attribute15                   IN pa_project_requests.attribute15%TYPE default null,
        p_create_rel_flag               IN   VARCHAR2 default null,
        p_api_version                   IN   NUMBER := 1.0,
        p_init_msg_list                 IN   VARCHAR2 default null,
        p_commit                        IN   VARCHAR2 default null,
        p_validate_only                 IN   VARCHAR2 default null,
        p_max_msg_count                 IN   NUMBER default null )
    RETURN VARCHAR2;
END xxorc_create_pa_request_pkg;
/
