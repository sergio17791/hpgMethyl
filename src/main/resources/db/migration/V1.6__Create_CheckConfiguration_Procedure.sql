CREATE FUNCTION check_configuration() RETURNS trigger AS $check_configuration$
    DECLARE
    	_configuration_rows INTEGER;
	BEGIN
        IF (TG_OP = 'DELETE') THEN
            RAISE EXCEPTION 'You cannot delete the configuration row';
        ELSIF (TG_OP = 'INSERT') THEN
            SELECT count(*) INTO _configuration_rows FROM configuration;
            IF _configuration_rows > 0 THEN
            	RAISE EXCEPTION 'Only one configuration row can exist';
            END IF;
            RETURN NEW;
        END IF;
        RETURN NULL;
    END;
$check_configuration$ LANGUAGE plpgsql;

CREATE TRIGGER check_configuration BEFORE INSERT OR DELETE ON configuration
    FOR EACH ROW EXECUTE PROCEDURE check_configuration();