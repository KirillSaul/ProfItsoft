import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import Button from 'components/Button';
import TextField from "components/TextField";
import MenuItem from "components/MenuItem";
import {useDispatch, useSelector} from "react-redux";
import productActions from "../store/actions/productActions";
import useAccessValidate from "../../../hooks/useAccessValidate";
import categoryActions from "../store/actions/categoryActions";
import Link from "../../../components/Link";

const CreateEditProduct = ({authorities}) => {
    const canSeeList = useAccessValidate({
        ownedAuthorities: authorities,
        neededAuthorities: ['МОЖНО_ВОТ_ЭТУ_ШТУКУ'],
    });
    const initialStateProduct = {id: null, name: null, categoryId: null}
    const [newProduct, setNewProduct] = useState(initialStateProduct);
    const [newCategories, setNewCategories] = useState([]);

    const dispatch = useDispatch();
    const {productId} = useParams()
    useEffect(() => {
        if (productId !== undefined) {
            dispatch(productActions.getProductById(productId))
        } else {
            dispatch(productActions.setProductIsCreating())
        }
        dispatch(categoryActions.getCategories())
    }, [])

    const product = useSelector((state) => {
        return state.productReducer.product
    })

    const categories = useSelector((state) => {
        return state.categoryReducer.categories;
    })

    useEffect(() => {
        setNewProduct((prevState) => ({...prevState, ...product}));
    }, [product])

    useEffect(() => {
        setNewCategories((prevState) => ([...prevState, ...categories]));
        if (newProduct.categoryId === null && categories.length !== 0) {
            setNewProduct((prevState) => ({...prevState, categoryId: categories[0].id}));
        }
    }, [categories])

    return (

        <div>
            {canSeeList &&
                <div>
                    <div>
                        <TextField label="Name:" value={newProduct.name !== null ? newProduct.name : ""}
                                   onChange={
                                       (e) => {
                                           setNewProduct((prevState) => ({...prevState, name: e.target.value}))
                                       }}>
                        </TextField>
                    </div>
                    <div>

                        <TextField
                            id="standard-select-currency"
                            select
                            label="Category:"
                            value={newProduct.categoryId !== null
                                ? newProduct.categoryId
                                : newCategories.length !== 0
                                    ? newCategories[0].id
                                    : ""}
                            onChange={(e) => {
                                setNewProduct((prevState) => ({...prevState, categoryId: e.target.value}))
                            }}
                        >
                            {newCategories.map((category) => (
                                <MenuItem key={category.id} value={category.id}>
                                    {category.name}
                                </MenuItem>
                            ))}

                        </TextField>
                    </div>
                    <Link to={"/productList"}>
                        <Button>Back</Button>
                    </Link>
                    <Button onClick={() => {
                        productId === undefined
                            ? dispatch(productActions.postProduct(newProduct))
                            : dispatch(productActions.putProduct(newProduct))
                    }}>Save</Button>
                </div>}
        </div>

    )

}
export default CreateEditProduct