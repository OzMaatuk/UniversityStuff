
import wmi

def main():
    rval = 0  # Default: Check passes.

    # Initialize WMI objects and query.
    wmi_o = wmi.WMI('.')
    wql = ("SELECT * FROM Win32_NTLogEvent WHERE Logfile="
           "'Application' AND EventCode='1003'")

    # Query WMI object.
    wql_r = wmi_o.query(wql)

    print wql_r

    if len(wql_r):
        rval = -1  # Check fails.

    return rval



if __name__ == '__main__':
    main()