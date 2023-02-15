import React from 'react';
import {applyMiddleware, combineReducers, createStore} from 'redux';
import {Provider} from 'react-redux';
import thunkMiddleware from 'redux-thunk';
import withAuthorities from 'decorators/withAuthorities';
import ProductList from './containers/ProductList';
import reducer from './store/reducers/reducer';

const rootReducer = combineReducers({
    reducer,
});
const store = createStore(
    rootReducer,
    applyMiddleware(thunkMiddleware),
);

export default withAuthorities(props => (
    <Provider store={store}>
        <ProductList {...props} />
    </Provider>
));