import React, { useState, useEffect } from 'react';

function LogList() {
    const [logs, setLogs] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchLogs = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/logs/all');
                if (!response.ok) {
                    throw new Error('Failed to fetch logs');
                }
                const data = await response.json();
                console.log('Fetched logs:', data); // Logowanie danych
                setLogs(data);
            } catch (error) {
                console.error('Error fetching logs:', error); // Logowanie błędów
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchLogs();
    }, []);

    if (loading) {
        return <p>Loading logs...</p>;
    }

    if (error) {
        return <p className="error">{error}</p>;
    }

    return (
        <div>
            <h2>Log List</h2>
            <ul>
                {logs.map(log => (
                    <li key={log.id.chars}>
                        <inf>Type: {log.type.chars} - User: {log.userId.chars} - Date: {log.date.chars} - Chest ID: {log.chestId.chars}</inf>
                        <div>
                            <h4>Details:</h4>
                            {log.type.chars === 'CHEST_PURCHASE' && log.details && (
                                <ul>
                                    <li>Chest Price: {log.details.chestPrice.chars}</li>
                                    <li>Quantity: {log.details.quantity.chars}</li>
                                    <li>Description: {log.details.description.chars}</li>
                                </ul>
                            )}
                            {log.type.chars === 'CHEST_OPEN' && log.details && (
                                <ul>
                                    <li>Skin Opened ID: {log.details.skinOpenedId.chars}</li>
                                    <li>Description: {log.details.description.chars}</li>
                                </ul>
                            )}
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default LogList;
