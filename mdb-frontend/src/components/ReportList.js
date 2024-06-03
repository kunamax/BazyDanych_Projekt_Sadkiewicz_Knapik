import React, { useState, useEffect } from 'react';

function ReportList() {
    const [usersTotalSkinsValues, setUsersTotalSkinsValues] = useState([]);
    const [userTotalSpending, setUserTotalSpending] = useState([]);
    const [skinReport, setSkinReport] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchReports = async () => {
            try {
                const response1 = await fetch('http://localhost:8080/api/reports/users-total-skins-values');
                const data1 = await response1.json();
                console.log('Users Total Skins Values:', data1);
                setUsersTotalSkinsValues(data1);

                const response2 = await fetch('http://localhost:8080/api/reports/skin-report');
                const data2 = await response2.json();
                console.log('Skin Report:', data2);
                setSkinReport(data2);

                const userId = '665cfd7c4b58ee76868aca22';
                const response3 = await fetch(`http://localhost:8080/api/reports/user-total-spending/${userId}`);
                const data3 = await response3.json();
                console.log('User Total Spending:', data3);
                setUserTotalSpending(data3);

            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchReports();
    }, []);

    if (loading) {
        return <p>Loading reports...</p>;
    }

    if (error) {
        return <p className="error">{error}</p>;
    }

    return (
        <div>
            <h2>Users Total Skins Values</h2>
            <ul>
                {Array.isArray(usersTotalSkinsValues) ? usersTotalSkinsValues.map(report => (
                    <li key={report._id.chars}>
                        Name: {report.name.chars}, Total Value: {report.totalValue.chars}
                    </li>
                )) : <p>No data available</p>}
            </ul>

            <h2>User Total Spending</h2>
            <ul>
                {Array.isArray(userTotalSpending) ? userTotalSpending.map(report => (
                    <li key={report._id.chars}>
                        UserId: {report._id.chars}, Total Amount: {report.totalAmount.chars}
                    </li>
                )) : <p>No data available</p>}
            </ul>

            <h2>Skin Report</h2>
            <ul>
                {Array.isArray(skinReport) ? skinReport.map(report => (
                    <li key={report._id.chars}>
                        Skin ID: {report._id.chars}, Count: {report.count.chars}, Total Spent: {report.totalSpent.chars}
                    </li>
                )) : <p>No data available</p>}
            </ul>
        </div>
    );
}

export default ReportList;
