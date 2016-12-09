create or replace PACKAGE BODY xxorc_create_pa_request_pkg
AS
  FUNCTION create_project_request(
p_request_name                IN pa_project_requests.request_name%TYPE,
      p_request_type                IN pa_project_requests.request_type%TYPE,
      p_request_status_code         IN pa_project_requests.status_code%TYPE,
      p_request_status_name         IN pa_project_statuses.project_status_name%TYPE,
      p_description                 IN pa_project_requests.description%TYPE,
      p_expected_proj_approval_date IN pa_project_requests.expected_project_approval_date%TYPE,
      p_closed_date                 IN pa_project_requests.closed_date%TYPE,
      p_source_type                 IN pa_project_requests.source_type%TYPE :='ORACLE_APPLICATION',
      p_application_id              IN pa_project_requests.application_id%TYPE,
      p_source_id                   IN NUMBER,
      p_source_object               IN pa_object_relationships.object_type_from%TYPE,
      p_source_reference            IN pa_project_requests.source_reference%TYPE,
      p_value                       IN pa_project_requests.value%TYPE,
      p_currency_code               IN pa_project_requests.currency_code%TYPE,
      p_cust_party_id               IN pa_project_requests.cust_party_id%TYPE,
      p_cust_party_name             IN hz_parties.party_name%TYPE,
      p_cust_party_site_id          IN pa_project_requests.cust_party_site_id%TYPE,
      p_cust_party_site_name        IN hz_party_sites.party_site_name%TYPE,
      p_cust_account_id             IN pa_project_requests.cust_account_id%TYPE,
      p_cust_account_name           IN hz_cust_accounts.account_name%TYPE,
      p_source_org_id               IN pa_project_requests.source_org_id%TYPE,
      p_attribute_category          IN pa_project_requests.attribute_category%TYPE,
      p_attribute1                  IN pa_project_requests.attribute1%TYPE,
      p_attribute2                  IN pa_project_requests.attribute2%TYPE,
      p_attribute3                  IN pa_project_requests.attribute3%TYPE,
      p_attribute4                  IN pa_project_requests.attribute4%TYPE,
      p_attribute5                  IN pa_project_requests.attribute5%TYPE,
      p_attribute6                  IN pa_project_requests.attribute6%TYPE,
      p_attribute7                  IN pa_project_requests.attribute7%TYPE,
      p_attribute8                  IN pa_project_requests.attribute8%TYPE,
      p_attribute9                  IN pa_project_requests.attribute9%TYPE,
      p_attribute10                 IN pa_project_requests.attribute10%TYPE,
      p_attribute11                 IN pa_project_requests.attribute11%TYPE,
      p_attribute12                 IN pa_project_requests.attribute12%TYPE,
      p_attribute13                 IN pa_project_requests.attribute13%TYPE,
      p_attribute14                 IN pa_project_requests.attribute14%TYPE,
      p_attribute15                 IN pa_project_requests.attribute15%TYPE,
      p_create_rel_flag             IN VARCHAR2,
      p_api_version                 IN NUMBER := 1.0,
      p_init_msg_list               IN VARCHAR2,
      p_commit                      IN VARCHAR2,
      p_validate_only               IN VARCHAR2,
      p_max_msg_count               IN NUMBER)
    RETURN VARCHAR2
  AS
    l_ret_status VARCHAR2(2400);
    l_request_id pa_project_requests.request_id%TYPE; --File.Sql.39 bug 4440895
    l_request_number pa_project_requests.request_number%TYPE;
    l_return_status VARCHAR2(2400);
    l_msg_count     NUMBER; --File.Sql.39 bug 4440895
    l_msg_data      VARCHAR2(24000);
  BEGIN
    INSERT INTO XXORC_CREATE_PA_REQUEST VALUES
      (297382,'Begin'
      );
    COMMIT;
   
   INSERT INTO XXORC_CREATE_PA_REQUEST VALUES
      (297382,'Begin-'||p_request_name
      );
      INSERT INTO XXORC_CREATE_PA_REQUEST VALUES
      (297382,'Begin1-'||p_request_type
      );
       INSERT INTO XXORC_CREATE_PA_REQUEST VALUES
      (297382,'Begin2-'||p_request_status_code
      );
    COMMIT;
    
    PA_PROJECT_REQUEST_PUB.CREATE_PROJECT_REQUEST ( p_request_name => p_request_name, p_request_type => p_request_type, p_request_status_code => p_request_status_code,
    --p_request_status_code           =>null,
    p_request_status_name => NULL,
    --p_description                   =>'Project Request Test',
    p_description => NULL, p_expected_proj_approval_date => NULL, p_closed_date => NULL, p_source_type => 'ORACLE_APPLICATION', p_application_id => NULL, p_source_reference => NULL, p_source_id => NULL,
    --p_source_object                 =>'AS_LEADS',
    p_source_object => NULL, p_value => NULL, p_currency_code => 'USD', p_cust_party_id => NULL, p_cust_party_name => NULL, p_cust_party_site_id => NULL, p_cust_party_site_name => NULL, p_cust_account_id => NULL, p_cust_account_name => NULL, p_source_org_id => NULL, p_attribute_category => NULL,
    -- p_attribute1                    => 'Test Project Request',
    p_attribute1 => NULL, p_attribute2 => NULL, p_attribute3 => NULL, p_attribute4 => NULL, p_attribute5 => NULL, p_attribute6 => NULL, p_attribute7 => NULL, p_attribute8 => NULL, p_attribute9 => NULL, p_attribute10 => NULL, p_attribute11 => NULL, p_attribute12 => NULL, p_attribute13 => NULL, p_attribute14 => NULL, p_attribute15 => NULL,
    --p_create_rel_flag               =>'Y',
    p_create_rel_flag => NULL, p_api_version => 1.0, p_init_msg_list => NULL, p_commit => NULL, p_validate_only => NULL, p_max_msg_count => NULL, x_request_id => l_request_id, x_request_number => l_request_number, x_return_status => l_return_status, x_msg_count => l_msg_count, x_msg_data => l_msg_data);
    IF l_return_status = fnd_api.g_ret_sts_success THEN
      COMMIT;
      l_ret_status:='Success creating Project Request,Request_id-'||l_request_id;
    ELSE
      l_ret_status:='Failure creating Project Request';
    END IF;
    INSERT INTO XXORC_CREATE_PA_REQUEST VALUES
      (297382,l_ret_status
      );
      commit;
    
    RETURN l_ret_status;
  EXCEPTION
  WHEN OTHERS THEN
    l_ret_status:=SQLERRM;
    INSERT INTO XXORC_CREATE_PA_REQUEST VALUES
      (297381,l_ret_status
      );
    COMMIT;
  END create_project_request;
END xxorc_create_pa_request_pkg;
