import React, { useEffect, useState } from 'react';

function LogList() {
    const [logs, setLogs] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch('/api/logs')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => setLogs(data))
            .catch(error => setError(error.message));
    }, []);

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div>
            <h2>Logs</h2>
            <ul>
                {logs.map(log => (
                    <li key={log._id}>
                        {log.type} - User: {log.user_id} - Date: {log.date}
                        <ul>
                            {log.details.chest_price && (
                                <li>Chest Price: {log.details.chest_price} - Quantity: {log.details.quantity}</li>
                            )}
                            {log.details.skin_opened_id && (
                                <li>Skin Opened ID: {log.details.skin_opened_id}</li>
                            )}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default LogList;
