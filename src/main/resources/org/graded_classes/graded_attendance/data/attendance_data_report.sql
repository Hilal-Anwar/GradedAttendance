-- Query 1: Count of missing dates, formatted to align with the second query
WITH RECURSIVE dates(date) AS (
    VALUES('2025-09-06')
    UNION ALL
    SELECT date(date, '+1 day')
FROM dates
WHERE date < date('now')
    )
SELECT
    'Missing Dates' AS ed_no,
    COUNT(*) AS attendance_count
FROM
    dates
        LEFT JOIN
    Attendance ON dates.date = Attendance.date
WHERE
    Attendance.date IS NULL
UNION ALL
-- Query 2: Attendance count grouped by ed_no
SELECT
    ed_no,
    count(ed_no)
FROM
    Attendance
WHERE
    date >= '2025-09-06' AND date <= '2025-10-31'
  AND
    check_in is not NULL
GROUP BY
    ed_no;
