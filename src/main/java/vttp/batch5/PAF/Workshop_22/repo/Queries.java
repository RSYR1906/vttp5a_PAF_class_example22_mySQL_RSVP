package vttp.batch5.PAF.Workshop_22.repo;

public class Queries {

        public static final String SQL_SELECT_ALL_RSVP = """
                            SELECT * FROM rsvp;
                        """;

        public static final String SQL_SELECT_RSVP_BY_NAME = """
                        SELECT * FROM rsvp WHERE name LIKE ?;
                        """;

        public static final String SQL_INSERT_NEW_RSVP = """
                        INSERT INTO rsvp(
                            name, email, phone, confirmDate, comments
                        )
                        VALUES
                            (?,?, ?, ?, ?);
                                    """;

        public static final String SQL_UPDATE_RSVP = """
                                UPDATE rsvp SET
                                name = ?,
                                email = ?,
                                phone = ?,
                                confirmDate = ?,
                                comments = ?
                        WHERE LOWER(email) = LOWER(?);
                                """;

        public static final String SQL_COUNT_RSVP = """
                        SELECT COUNT(*) AS total_no_RSVP FROM rsvp;
                        """;

        public static final String SQL_CHECK_RSVP_BY_EMAIL = """
                        SELECT COUNT(*) FROM rsvp WHERE email = ?;
                        """;

}
