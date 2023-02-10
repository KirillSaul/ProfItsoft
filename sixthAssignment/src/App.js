import { createStore, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import reducer from "./store/reducer/Reducer";
import Calculator from "./component/Calculator";

const store = createStore(reducer)
const App = () =>{
    return (
        <Provider store={store}>
        <Calculator/>
        </Provider>
    );
}
export default App;
