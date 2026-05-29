import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import api from '../api/axios';

const DashboardPage = () => {
    const [tasks, setTasks] = useState([]);
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [priority, setPriority] = useState('MEDIUM');
    const [status, setStatus] = useState('TODO');
    const [dueDate, setDueDate] = useState('');
    const [editingTask, setEditingTask] = useState(null);
    const { logout } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        fetchTasks();
    }, []);

    const fetchTasks = async () => {
        try {
            const response = await api.get('/api/tasks');
            setTasks(response.data);
        } catch (err) {
            console.error('Failed to fetch tasks');
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const taskData = { title, description, priority, status, dueDate };
        try {
            if (editingTask) {
                await api.put(`/api/tasks/${editingTask.id}`, taskData);
            } else {
                await api.post('/api/tasks', taskData);
            }
            fetchTasks();
            resetForm();
        } catch (err) {
            console.error('Failed to save task');
        }
    };

    const handleDelete = async (id) => {
        try {
            await api.delete(`/api/tasks/${id}`);
            fetchTasks();
        } catch (err) {
            console.error('Failed to delete task');
        }
    };

    const handleEdit = (task) => {
        setEditingTask(task);
        setTitle(task.title);
        setDescription(task.description);
        setPriority(task.priority);
        setStatus(task.status);
        setDueDate(task.dueDate);
    };

    const resetForm = () => {
        setEditingTask(null);
        setTitle('');
        setDescription('');
        setPriority('MEDIUM');
        setStatus('TODO');
        setDueDate('');
    };

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    return (
        <div className="dashboard">
            <div className="dashboard-header">
                <h2>My Tasks</h2>
                <button onClick={handleLogout}>Logout</button>
            </div>

            <form onSubmit={handleSubmit} className="task-form">
                <h3>{editingTask ? 'Edit Task' : 'New Task'}</h3>
                <input
                    type="text"
                    placeholder="Title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                />
                <input
                    type="text"
                    placeholder="Description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                />
                <select value={priority} onChange={(e) => setPriority(e.target.value)}>
                    <option value="LOW">Low</option>
                    <option value="MEDIUM">Medium</option>
                    <option value="HIGH">High</option>
                </select>
                <select value={status} onChange={(e) => setStatus(e.target.value)}>
                    <option value="TODO">To Do</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="DONE">Done</option>
                </select>
                <input
                    type="date"
                    value={dueDate}
                    onChange={(e) => setDueDate(e.target.value)}
                />
                <button type="submit">{editingTask ? 'Update Task' : 'Add Task'}</button>
                {editingTask && <button type="button" onClick={resetForm}>Cancel</button>}
            </form>

            <div className="task-list">
                {tasks.map(task => (
                    <div key={task.id} className="task-card">
                        <div className="task-info">
                            <h4>{task.title}</h4>
                            <p>{task.description}</p>
                            <span className="badge">{task.priority}</span>
                            <span className="badge">{task.status}</span>
                            {task.dueDate && <span>Due: {task.dueDate}</span>}
                        </div>
                        <div className="task-actions">
                            <button onClick={() => handleEdit(task)}>Edit</button>
                            <button onClick={() => handleDelete(task.id)}>Delete</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default DashboardPage;