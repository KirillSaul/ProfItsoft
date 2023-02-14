const initialState = {
  availableItems: [
    'как+выучить+js',
    'somePath',
    'Картинка',
    'anotherPath',
    undefined,
    "productList",
  ],

};

export default (state = initialState, {type, payload}) => {
  switch (type) {

    default: return state;
  }
}
