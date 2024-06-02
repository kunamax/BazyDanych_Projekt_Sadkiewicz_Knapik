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
                setLogs(data);
            } catch (error) {
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
                    <li key={log.id}>
                        <p>Type: {log.type} - User: {log.userId} - Date: {log.date}</p>
                        <div>
                            <h4>Details:</h4>
                            {log.type === 'CHEST_PURCHASE' && log.details && (
                                <ul>
                                    <li>Chest Price: {log.details.chestPrice}</li>
                                    <li>Quantity: {log.details.quantity}</li>
                                    <li>Description: {log.details.description}</li>
                                </ul>
                            )}
                            {log.type === 'CHEST_OPEN' && log.details && (
                                <ul>
                                    <li>Skin Opened ID: {log.details.skinOpenedId}</li>
                                    <li>Description: {log.details.description}</li>
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
