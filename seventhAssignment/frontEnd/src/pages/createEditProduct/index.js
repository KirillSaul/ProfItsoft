import React from 'react';
import {applyMiddleware, combineReducers, createStore} from 'redux';
import {Provider} from 'react-redux';
import thunkMiddleware from 'redux-thunk';
import withAuthorities from 'decorators/withAuthorities';
import productReducer from './store/reducers/productReducer';
import categoryReducer from "./store/reducers/categoryReducer";
import CreateEditProduct from "./containers/CreateEditProduct";

const rootReducer = combineReducers({
    productReducer,categoryReducer
});
const store = createStore(
    rootReducer,
    applyMiddleware(thunkMiddleware),
);

export default withAuthorities(props => (
    <Provider store={store}>
        <CreateEditProduct {...props} />
    </Provider>
));